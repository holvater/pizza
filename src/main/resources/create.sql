drop table delivery_order;
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
    active boolean not null,
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
    id_order bigint not null,
    foreign key (id_crust) references corporate_catalog (id),
    foreign key (id_order) references delivery_order (id),
    foreign key (id_size) references corporate_catalog (id)
);

create table delivery_order
(
    id bigint primary key auto_increment,
    creation_date timestamp not null,
    id_address bigint not null,
    username varchar(20) not null,
    foreign key (id_address) references address (id),
    foreign key (username) references user (username)
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
    name varchar(50) not null,
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

insert into corporate_catalog values
(1,null,'Pizza Sizes','Pizza sizes available',1),
(3,1,'Small','Small pizza, 4 slices',1),
(4,1,'Medium','Medium pizza, 6 slices',1),
(5,1,'Large','Large pizza, 8 slices',1),
(6,1,'Extreme','Extreme pizza, 12 slices',1),
(7,null,'Pizza crusts','Pizza crusts available',1),
(8,7,'Traditional','Our traditional crust recipe',1),
(9,7,'Thin & Crispy','Thin & crispy crust directly from Italy',1),
(10,null,'Pizza Toppings','Pizza toppings available',1),
(11,10,'Ham','Ham',1),(12,10,'Pepperoni','Pepperoni',1),
(13,10,'Pineapple','Pineapple',1),
(14,10,'Sausage','Sausage',1),
(15,10,'Bacon','Bacon',1),
(16,10,'Extra Cheese','Extra Cheese',1),
(17,10,'Salami','Salami',1),
(18,null,'Roles','Roles',1),
(19,18,'USERS','USERS',1),
(20,19,'CUSTOMERS','CUSTOMERS',1),
(21,20,'REGULAR','REGULAR CUSTOMER',1),
(22,19,'ADMINISTRATORS','ADMINISTRATORS',1),
(23,22,'ROOT','ROOT',1),
(24,null,'Countries','Countries available for delivery',1),
(25,24,'México','México',1),
(26,25,'D.F.','Distrito Federal',1),
(27,25,'Nuevo León','Nuevo León',1),
(28,24,'USA','United States',1),
(29,28,'California','California',1);

insert into user values
('root','root',1),
('john','john',1);

insert into role (id_role,id_group,id_group_type,username,active) values
(23,22,19,'root', 1),
(21,20,19,'john', 1);

insert into address (name,street,exterior_number,interior_number,zip_code,active,username,id_state,id_country) values
('My House','Miguel Laurent','123',null,'03100',1,'john',26,25),
('Office','Monte Rosas','456','1A','03456',1,'john',27,25);