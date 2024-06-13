'use client';
import Secret from '@/core/Secret';
import { findSecret } from '@/core/client';
import { AxiosResponse } from 'axios';
import React, { FormEvent } from 'react';

export default function Page({ params }: { params: { slug: string } }) {
  const [cipherText, setCipherText] = React.useState('');

  React.useEffect(() => {
    const response: Promise<AxiosResponse> = findSecret(params.slug);
    response.then((r) => {
      console.log(' [*] Response status: ', r.status);
      console.log(' [*] Response data: ', r.data);

      setCipherText(r.data.secret);
    });
  }, []);

  async function onDecyrpt(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();

    console.log('[*] Decrypting data...');
    const formData = new FormData(event.currentTarget);

    const cipherText = formData.get('cipherText')?.toString() ?? '';
    const pass = formData.get('pass')?.toString() ?? '';

    const secret = new Secret(pass);

    const decryptedText = secret.decrypt(cipherText) ?? '';

    console.log(' [*] Decrypted text: ', decryptedText);

    setCipherText(decryptedText);
  }

  const changeCipherText = (e: any) => {
    let value = e.target.value;
    setCipherText(value);
  };

  return (
    <div className="flex h-screen">
      <div className="m-auto">
        <form onSubmit={onDecyrpt}>
          <p>Ciphertext:</p>
          <textarea
            id="cipherText"
            name="cipherText"
            className="border-2 outline-none border-green-400 rounded-md p-2 w-96 h-40 text-wrap focus:border-black"
            maxLength={200}
            defaultValue={cipherText}
            onChange={(e) => changeCipherText(e)}
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
          <button
            type="submit"
            className="text-white bg-blue-700 hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 w-96"
          >
            Decrypt
          </button>
        </form>
      </div>
    </div>
  );
}
