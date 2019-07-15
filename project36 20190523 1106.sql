-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.41-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema project36
--

CREATE DATABASE IF NOT EXISTS project36;
USE project36;

--
-- Definition of table `billingaddress`
--

DROP TABLE IF EXISTS `billingaddress`;
CREATE TABLE `billingaddress` (
  `addressid` int(10) unsigned NOT NULL auto_increment,
  `addresslineone` varchar(255) default NULL,
  `addresslinetwo` varchar(255) default NULL,
  `country` varchar(45) default NULL,
  `city` varchar(45) default NULL,
  `statename` varchar(45) default NULL,
  `emailid` varchar(45) default NULL,
  `phone` varchar(45) default NULL,
  PRIMARY KEY  (`addressid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `billingaddress`
--

/*!40000 ALTER TABLE `billingaddress` DISABLE KEYS */;
INSERT INTO `billingaddress` (`addressid`,`addresslineone`,`addresslinetwo`,`country`,`city`,`statename`,`emailid`,`phone`) VALUES 
 (1,'Scout Market','Kakrile','Bangladesh','Dhaka','Dhaka','test@test.com','089870'),
 (2,'127/a','Mohommadpur','Bangladesh','Dhaka','Dhaka','test@test.com','070870'),
 (3,'Dhaka','Dhaka','Bangladesh','Dhaka','Dhaka','tes2@test.com','0899435435');
/*!40000 ALTER TABLE `billingaddress` ENABLE KEYS */;


--
-- Definition of table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `catid` varchar(255) NOT NULL,
  `catname` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`catid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`catid`,`catname`,`description`) VALUES 
 ('1','Mobile','Mobile'),
 ('2','Test','asdasfs'),
 ('3','Meat','Meat');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


--
-- Definition of table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
CREATE TABLE `orderdetails` (
  `ordetailid` int(10) unsigned NOT NULL auto_increment,
  `orderid` int(10) unsigned default NULL,
  `productid` int(10) unsigned default NULL,
  `quantity` int(10) unsigned default NULL,
  `price` double default NULL,
  PRIMARY KEY  (`ordetailid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderdetails`
--

/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` (`ordetailid`,`orderid`,`productid`,`quantity`,`price`) VALUES 
 (1,1,9,1,700),
 (2,1,10,1,27000),
 (3,2,9,1,700),
 (4,2,3,2,678),
 (5,3,6,1,4000),
 (6,4,9,1,700),
 (7,4,13,1,400),
 (8,9,6,1,4000);
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;


--
-- Definition of table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `productid` int(10) unsigned NOT NULL auto_increment,
  `catid` varchar(255) default NULL,
  `supplierid` varchar(255) default NULL,
  `productdescription` varchar(255) default NULL,
  `productname` varchar(100) default NULL,
  `productprice` double default NULL,
  `productquantity` int(10) unsigned default NULL,
  `purchasedate` date default NULL,
  `saleprice` double default NULL,
  PRIMARY KEY  (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`productid`,`catid`,`supplierid`,`productdescription`,`productname`,`productprice`,`productquantity`,`purchasedate`,`saleprice`) VALUES 
 (3,'1','2','fdsf','dsf',4534,3,'2018-11-26',678),
 (4,'1','2','fdsf','dsf',0,0,'2018-11-26',0),
 (5,'2','1','fdsf','fdsf',0,0,'2018-11-26',0),
 (6,'1','1','Mobile','Mobile',3000,0,'2018-11-26',4000),
 (7,'1','1','www','www',0,0,'2018-11-28',0),
 (9,'1','1','fdsf','dsf',600,0,'2018-12-01',700),
 (10,'1','2','Samsung','Samsung Mobile',26000,1,'2018-12-03',27000),
 (11,'1','1','Nice ','New Product',300,2,'2018-12-15',400),
 (13,'3','3','Test','Test',300,0,'2018-12-17',400);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;


--
-- Definition of table `productorder`
--

DROP TABLE IF EXISTS `productorder`;
CREATE TABLE `productorder` (
  `orderid` int(10) unsigned NOT NULL auto_increment,
  `orderdate` date default NULL,
  `useremail` varchar(45) default NULL,
  `orderstatus` varchar(45) default NULL,
  `addressid` int(10) unsigned default NULL,
  PRIMARY KEY  (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productorder`
--

/*!40000 ALTER TABLE `productorder` DISABLE KEYS */;
INSERT INTO `productorder` (`orderid`,`orderdate`,`useremail`,`orderstatus`,`addressid`) VALUES 
 (1,'2018-12-05','test@test.com','ordered',1),
 (2,'2018-12-08','test@test.com','ordered',2),
 (3,'2018-12-08','test@test.com','ordered',1),
 (4,'2018-12-20','test@test.com','ordered',2),
 (5,'2019-05-23','tes2@test.com','ordered',3),
 (6,'2019-05-23','tes2@test.com','ordered',3),
 (7,'2019-05-23','tes2@test.com','ordered',3),
 (8,'2019-05-23','tes2@test.com','ordered',3),
 (9,'2019-05-23','tes2@test.com','ordered',3);
/*!40000 ALTER TABLE `productorder` ENABLE KEYS */;


--
-- Definition of table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `supplierid` varchar(255) NOT NULL,
  `suppliername` varchar(45) default NULL,
  `supplieraddress` varchar(255) default NULL,
  `supplierphone` varchar(45) default NULL,
  PRIMARY KEY  USING BTREE (`supplierid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` (`supplierid`,`suppliername`,`supplieraddress`,`supplierphone`) VALUES 
 ('1','TCLD','Dhaka','017654321'),
 ('2','IBM','Dhaka','017654324'),
 ('3','Samsung Inc.','Dhaka','017654325');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `emailid` varchar(255) NOT NULL,
  `phone` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  PRIMARY KEY  (`emailid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`emailid`,`phone`,`username`) VALUES 
 ('tes2@test.com','+88123456','aaa'),
 ('test3@test.com','+881712','bari'),
 ('test4@test.com','+885345','aaa'),
 ('test@test.com','1234567','bari');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `emailid` varchar(255) NOT NULL,
  `password` varchar(255) default NULL,
  `role` varchar(255) default NULL,
  `status` varchar(255) default NULL,
  PRIMARY KEY  (`emailid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userrole`
--

/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` (`emailid`,`password`,`role`,`status`) VALUES 
 ('tes2@test.com','123','ROLE_USER','True'),
 ('test3@test.com','123','ROLE_USER','True'),
 ('test4@test.com','123','ROLE_USER','True'),
 ('test@test.com','123','ROLE_ADMIN','True');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
