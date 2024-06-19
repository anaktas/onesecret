all: up

up:
	cp .env.local frontend/.env.local
	docker compose build
	docker compose up -d

clean:
	docker compose down
	docker rmi onesecret-onesecret-frontend
	docker rmi onesecret-onesecret-backend
