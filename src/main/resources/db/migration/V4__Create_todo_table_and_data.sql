create table todos (
   id int unsigned primary key auto_increment,
   text varchar(100) not null,
   done bit not null
);

insert into todos (text, done) values ('Done to do', 1);
insert into todos (text, done) values ('Undone to do', 0);