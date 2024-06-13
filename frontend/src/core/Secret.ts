import * as crypto from 'crypto';

export default class Secret {
  encoding: BufferEncoding = 'hex';

  key: string;

  constructor(k: string) {
    const temp = crypto.createHash('sha256').update(k).digest('hex');
    // sha256 creates a 64 bytes hash, we need only 32 bytes.
    // I should improve this somehow...
    this.key = temp.slice(0, temp.length / 2);
  }

  encrypt(plaintext: string) {
    try {
      const iv = crypto.randomBytes(16);
      const cipher = crypto.createCipheriv('aes-256-cbc', this.key, iv);

      const encrypted = Buffer.concat([
        cipher.update(plaintext, 'utf-8'),
        cipher.final()
      ]);

      return iv.toString(this.encoding) + encrypted.toString(this.encoding);
    } catch (e) {
      console.error(e);
    }
  }

  decrypt(cipher: string) {
    const { ivString, cipherString } = this.splitIVAndData(cipher);

    try {
      const iv = Buffer.from(ivString, this.encoding);
      const encryptedText = Buffer.from(cipherString, this.encoding);

      const decipher = crypto.createDecipheriv('aes-256-cbc', this.key, iv);

      const decrypted = decipher.update(encryptedText);
      return Buffer.concat([decrypted, decipher.final()]).toString();
    } catch (e) {
      console.error(e);
    }
  }

  private splitIVAndData(payload: string) {
    return {
      ivString: payload.slice(0, 32),
      cipherString: payload.slice(32)
    };
  }
}
