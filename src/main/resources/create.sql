
drop table address;
drop table role;
drop table user;
drop table pizza_topping;
drop table pizza;
drop table corporate_catalog;

create table corporate_catalog
(
    id bigint primary key auto_increment,
    catalog_group bigint,
    short_description varchar(30) not null,
    long_description varchar(300) not null,
    active boolean not null,
    foreign key (catalog_group) references corporate_catalog (id)
);

create table user
(
    username varchar(20) primary key,
    password varchar(20) not null,
    active boolean not null
);   

create table role
(
    id bigint primary key auto_increment,    
    id_role bigint not null,
    id_group bigint not null,
    id_group_type bigint not null,
    username varchar(20) not null,
    foreign key (id_role) references corporate_catalog (id),
    foreign key (id_group) references corporate_catalog (id),
    foreign key (id_group_type) references corporate_catalog (id),
    foreign key (username) references corporate_catalog (username)
);

create table pizza
(
    id bigint primary key auto_increment,
    id_crust bigint not null,
    id_size bigint not null,
    foreign key (id_crust) references corporate_catalog (id),
    foreign key (id_size) references corporate_catalog (id)
);

create table pizza_topping
(
    id_topping bigint,
    id_pizza bigint,
    foreign key (id_topping) references corporate_catalog (id),
    foreign key (id_pizza) references corporate_catalog (id),
    primary key(id_topping,id_pizza)
);

create table address
(
    id bigint primary key auto_increment,
    street varchar(100) not null,
    exterior_number varchar(5) not null,
    interior_number varchar(5),
    zip_code varchar(5) not null,    
    active boolean not null,
    username varchar(20) not null,
    id_state bigint not null,
    id_country bigint not null,    
    foreign key (username) references corporate_catalog (username),
    foreign key (id_state) references corporate_catalog (id),
    foreign key (id_country) references corporate_catalog (id)    
);

INSERT INTO corporate_catalog VALUES 
(1,NULL,'Pizza sizes','Pizza sizes available',1),
(3,1,'Small','Small pizza, 4 slices',1),
(4,1,'Medium','Medium pizza, 6 slices',1),
(5,1,'Large','Large pizza, 8 slices',1),
(6,1,'Extreme','Extreme pizza, 12 slices',1),
(7,NULL,'Pizza crusts','Pizza crusts available',1),
(8,7,'Traditional','Our traditional crust recipe',1),
(9,7,'Thin & Crispy','Thin & crispy crust directly from Italy',1),
(10,NULL,'Pizza toppings','Pizza toppings available',1),
(11,10,'Ham','Ham',1),(12,10,'Pepperoni','Pepperoni',1),
(13,10,'Pineapple','Pineapple',1),
(14,10,'Sausage','Sausage',1),
(15,10,'Bacon','Bacon',1),
(16,10,'Extra Cheese','Extra Cheese',1),
(17,10,'Salami','Salami',1);
