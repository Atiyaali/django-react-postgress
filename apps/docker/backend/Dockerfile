FROM python:3.10.12-slim-buster

WORKDIR /apps/backend

COPY ./apps/backend/ .

RUN python3 -m venv /opt/venv && \
    /opt/venv/bin/pip install --upgrade pip && \
    /opt/venv/bin/pip install -r requirements.txt && \
    chmod +x entrypoint.sh

CMD ["sh", "entrypoint.sh"]