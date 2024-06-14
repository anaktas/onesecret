all: up

up:
	docker compose build
	docker compose up -d

clean:
	docker compose down
	docker rmi onesecret-onesecret-frontend
