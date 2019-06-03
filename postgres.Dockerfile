FROM postgres:11

RUN apt-get update
RUN apt-get -y install postgresql-11-cron

RUN  apt-get clean && \
     rm -rf /var/cache/apt/* /var/lib/apt/lists/*

# Final set up
ENTRYPOINT ["/docker-entrypoint.sh"]

EXPOSE 5432
CMD ["postgres"]