create table movie (
    id int not null,
    title varchar(100) not null,
    description varchar(1000),
    rating float default 0,
    duration int,
    image varchar(200),
    trailer_link varchar(200),
    created_at timestamp,
    created_by varchar(200),
    updated_at timestamp,
    updated_by varchar(200),
    constraint id_movie_pkey primary key (id)
);