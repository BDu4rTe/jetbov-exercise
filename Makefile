ifneq (,$(wildcard ./.env))
    include .env
    export
endif

default: run

run:
	@echo "Runing the application..."
	@gradle bootRun

up:
	@echo "Up containers..."
	@docker compose up -d