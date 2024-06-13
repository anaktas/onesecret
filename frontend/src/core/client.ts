import axios, {
  AxiosRequestConfig,
  AxiosResponse,
  RawAxiosRequestHeaders
} from 'axios';

const client = axios.create({
  baseURL: 'http://localhost:8080' // TODO: This need to be an env variable.
});

export function sendSecret(secret: string): Promise<AxiosResponse> {
  return new Promise((resolve) => {
    const config: AxiosRequestConfig = {
      headers: {
        Accept: 'application/json'
      } as RawAxiosRequestHeaders
    };
    const data = { secret: secret };
    const response: any = client.post(`/api/secrets`, data, config);
    resolve(response);
  });
}

export function findSecret(slug: string): Promise<AxiosResponse> {
  return new Promise((resolve) => {
    const config: AxiosRequestConfig = {
      headers: {
        Accept: 'application/json'
      } as RawAxiosRequestHeaders
    };
    const response: any = client.get(`/api/secrets/` + slug, config);
    resolve(response);
  });
}
