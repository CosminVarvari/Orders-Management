
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `age` int(11) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



LOCK TABLES `client` WRITE;
INSERT INTO `client` (`id`, `Name`, `city`, `address`, `email`, `age`, `Phone`) VALUES (1,'Oprean Dan','Alba-Iulia','1 Decembrie 45','oprean_dan@gmail.com',20,'0741111111'),(2,'Vasilache Andrei','Piatra Neamt','Bulevardul Dacia 12','andrei.vasilache@gmail.com',20,'0741111112'),(3,'Ovidiu Varvari','Bistrita-Nasaud','Tudor Vladimirescu 2','ovidiu.varvari@gmail.com',24,'0741111113'),(4,'Diana Albu','Cluj-Napoca','Muncii 22','diana.albu@gmail.com',22,'0741111114'),(5,'David Cristian','Cluj-Napoca','Bulevardul 77','david.cristian@gmail.com',25,'0741111115');
UNLOCK TABLES;


DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  `idProduct` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idClient_idx` (`idClient`),
  KEY `idProduct_idx` (`idProduct`),
  CONSTRAINT `idClient` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`),
  CONSTRAINT `idProduct` FOREIGN KEY (`idProduct`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



LOCK TABLES `orders` WRITE;

INSERT INTO `orders` (`id`, `idClient`, `idProduct`, `quantity`, `date`) VALUES (1,1,1,3,'2022-01-09'),(2,14,3,10,'2022-03-22'),(3,6,4,3,'2022-02-19');

UNLOCK TABLES;


DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unitPrice` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



LOCK TABLES `product` WRITE;

INSERT INTO `product` (`id`, `Name`, `quantity`, `unitPrice`) VALUES (1,'Tricou',110,99),(2,'Lapte',137,5),(3,'Branza',10,33),(4,'Paste',60,13),(5,'Casti',8,200),(6,'Apa minerala',200,4),(7,'Adidasi',2,550),(8,'Incarcator',20,99);

UNLOCK TABLES;




