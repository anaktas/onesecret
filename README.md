# Onesecret

## Description

`Onesecret` is a replica (or poor relative...) of `onetimesecret.com`, but it's much more simplified.
It's a full end-to-end encryption platform with the following restriction:

1. Only password encrypted secrets are supported.
2. It doesn't support server side encryption, everything happens on your end.
3. Every secret is purged after 5 minutes.
4. It uses an in-memory database (H2) without persistence capabilities.

## Prerequisites

Just install docker, everything happens in the containers (even the build).

## Run

If you are on a linux, or have installed `make` on your system, just run

```
make up
```

for running it locally and

```
make clean
```

to stop and remove the containers.

If you don't have `make` installed on your system, well... just open the `Makefile` in the root of
the project and take a copy the corresponding commands which are executed in the corresponding
labels (`up` and `clean`).

By default the app is listening in port `5656`. If you want to make it run on a server, you have to
find your way out in order to set the proper env variables and make it publicly available.

## Stack

### Frontend

`NextJS`

### Backend

`Spring Boot` (java 21)
