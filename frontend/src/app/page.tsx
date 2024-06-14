'use client';
import Secret from '@/core/Secret';
import { sendSecret } from '@/core/client';
import { AxiosResponse } from 'axios';
import React, { FormEvent } from 'react';

export default function Home() {
  const [url, setUrl] = React.useState('');

  async function onEncyrpt(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();

    console.log('[*] Encrypting data...');
    const formData = new FormData(event.currentTarget);

    const plain = formData.get('plain')?.toString() ?? '';
    const pass = formData.get('pass')?.toString() ?? '';

    const secret = new Secret(pass);

    const encryptedText = secret.encrypt(plain) ?? '';

    const response: Promise<AxiosResponse> = sendSecret(encryptedText);
    response.then((r) => {
      console.log(' [*] Response status: ', r.status);
      console.log(' [*] Response data: ', r.data);

      if (r.status != 200) {
        alert('An error occurred, please try again later.');
        return;
      }

      let frontentUrl: string =
        process.env.NEXT_PUBLIC_FE_DECRYPT_URL || 'http://localhost:3000/secret/';
      setUrl(frontentUrl + r.data.slug);
    });
  }

  const changeUrl = (e: any) => {
    let value = e.target.value;
    setUrl(value);
  };

  return (
    <div className="flex h-screen">
      <div className="m-auto">
        <form onSubmit={onEncyrpt}>
          <p>Plaintext:</p>
          <textarea
            id="plain"
            name="plain"
            className="border-2 outline-none border-green-400 rounded-md p-2 w-96 h-40 text-wrap focus:border-black"
            maxLength={200}
          />
          <br />
          <br />
          <p>Password:</p>
          <input
            type="text"
            id="pass"
            name="pass"
            className="border-2 outline-none border-green-400 rounded-md p-2 w-96 h-10 focus:border-black"
            maxLength={30}
          />
          <br />
          <br />
          <p>Encrypted text url:</p>
          <input
            type="text"
            id="url"
            name="url"
            className="border-2 outline-none border-green-400 rounded-md p-2 w-96 h-10 focus:border-black"
            maxLength={30}
            defaultValue={url}
            onChange={(e) => changeUrl(e)}
          />
          <br />
          <br />
          <button
            type="submit"
            className="text-white bg-blue-700 hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 w-96"
          >
            Encrypt
          </button>
        </form>
      </div>
    </div>
  );
}
