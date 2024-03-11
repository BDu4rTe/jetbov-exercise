ifneq (,$(wildcard ./.env))
    include .env
    export
endif

default: run

run:
	@echo "Runing the application..."
	@gradlew bootRun

up:
	@echo "Up containers..."
	@docker compose up -d