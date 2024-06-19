FROM node:18-alpine

WORKDIR /opt/onesecret

COPY . .

RUN npm install

COPY .env.local .env.local

RUN npm run build

CMD ["npm", "start"]
