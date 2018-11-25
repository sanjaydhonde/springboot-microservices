-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 25, 2018 at 09:56 PM
-- Server version: 5.7.22-0ubuntu0.16.04.1
-- PHP Version: 7.0.32-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `Address`
--

CREATE TABLE `Address` (
  `ID` bigint(20) NOT NULL,
  `addLine1` varchar(255) DEFAULT NULL,
  `addLine2` varchar(255) DEFAULT NULL,
  `addLine3` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `pin` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Address`
--

INSERT INTO `Address` (`ID`, `addLine1`, `addLine2`, `addLine3`, `city`, `pin`, `state`) VALUES
(11, '202, C/2, Shree Laxmi Park Ph 1', 'Lokmanya Nagar', 'Thane West', 'Thane', '400606', 'Maharashtra'),
(12, '202, C/2, Shree Laxmi Park Ph 1', 'Lokmanya Nagar', 'Thane West', 'Thane', '400606', 'Maharashtra'),
(13, '202, C/2, Shree Laxmi Park Ph 1', 'Lokmanya Nagar', 'Thane West', 'Thane', '400606', 'Maharashtra');

-- --------------------------------------------------------

--
-- Table structure for table `Banners`
--

CREATE TABLE `Banners` (
  `ID` bigint(20) NOT NULL,
  `descri` varchar(255) DEFAULT NULL,
  `IsEnabled` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Banners`
--

INSERT INTO `Banners` (`ID`, `descri`, `IsEnabled`, `name`, `url`) VALUES
(1, 'banner1 description', 1, 'banner1', '/images/user1/banners/banner7.jpeg'),
(2, 'banner 2 desc', 1, 'banner2', '/images/user1/banners/banner1.jpeg'),
(3, 'banner3', 1, 'banner3', '/images/user1/banners/banner11.jpeg'),
(4, 'banner4', 1, 'banner4', '/images/user1/banners/banner9.jpeg'),
(5, 'banner5', 1, 'banner5', '/images/user1/banners/banner8.jpeg'),
(6, 'banner6', 1, 'banner6', '/images/user1/banners/banner6.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `CustomerAddresses`
--

CREATE TABLE `CustomerAddresses` (
  `dateFrom` datetime DEFAULT NULL,
  `dateTo` datetime DEFAULT NULL,
  `cust_ID` bigint(20) NOT NULL,
  `addr_ID` bigint(20) NOT NULL,
  `addressType` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CustomerAddresses`
--

INSERT INTO `CustomerAddresses` (`dateFrom`, `dateTo`, `cust_ID`, `addr_ID`, `addressType`) VALUES
(NULL, NULL, 12, 11, 'Home'),
(NULL, NULL, 14, 12, 'Home'),
(NULL, NULL, 15, 13, 'Home');

-- --------------------------------------------------------

--
-- Table structure for table `CustomerOrderProducts`
--

CREATE TABLE `CustomerOrderProducts` (
  `comment` varchar(255) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `customerOrders_ID` bigint(20) NOT NULL,
  `products_ID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `CustomerOrders`
--

CREATE TABLE `CustomerOrders` (
  `ID` bigint(20) NOT NULL,
  `cust_ID` bigint(20) DEFAULT NULL,
  `orderStatus` varchar(255) NOT NULL,
  `payment_ID` bigint(20) DEFAULT NULL,
  `totalAmount` double NOT NULL,
  `deliveryAddress_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `CustomerOrdersDelivery`
--

CREATE TABLE `CustomerOrdersDelivery` (
  `ID` bigint(20) NOT NULL,
  `dateReported` datetime DEFAULT NULL,
  `orders_ID` bigint(20) DEFAULT NULL,
  `deliveryStatus` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `CustomerPaymentMethods`
--

CREATE TABLE `CustomerPaymentMethods` (
  `ID` bigint(20) NOT NULL,
  `cardNumber` varchar(255) DEFAULT NULL,
  `dateFrom` datetime DEFAULT NULL,
  `dateTo` datetime DEFAULT NULL,
  `otherDetails` varchar(255) DEFAULT NULL,
  `customer_ID` bigint(20) DEFAULT NULL,
  `paymentMethods_ID` bigint(20) DEFAULT NULL,
  `orders_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Customers`
--

CREATE TABLE `Customers` (
  `ID` bigint(20) NOT NULL,
  `custId` bigint(20) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `lname` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Customers`
--

INSERT INTO `Customers` (`ID`, `custId`, `fname`, `lname`, `title`) VALUES
(12, 0, 'Sanjay', 'Dhonde', 'Mr'),
(14, 0, 'Sanjay', 'Dhonde', 'Mr'),
(15, 0, 'Sanjay', 'Dhonde', 'Mr');

-- --------------------------------------------------------

--
-- Table structure for table `ElectronicContact`
--

CREATE TABLE `ElectronicContact` (
  `ID` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `cust_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ElectronicContact`
--

INSERT INTO `ElectronicContact` (`ID`, `email`, `phone`, `cust_ID`) VALUES
(12, 'sanjay.dhonde1@gmail.com', '9987552270', 12),
(14, 'sanjay.dhonde2@gmail.com', '9987552273', 14),
(15, 'sanjay.dhonde3@gmail.com', '9987552278', 15);

-- --------------------------------------------------------

--
-- Table structure for table `EmailVerification`
--

CREATE TABLE `EmailVerification` (
  `ID` bigint(20) NOT NULL,
  `attempts` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `isVerified` tinyint(4) NOT NULL,
  `token` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1),
(1);

-- --------------------------------------------------------

--
-- Table structure for table `ProductCategories`
--

CREATE TABLE `ProductCategories` (
  `ID` bigint(20) NOT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `parentCatName` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ProductCategories`
--

INSERT INTO `ProductCategories` (`ID`, `descr`, `name`, `parentCatName`, `url`) VALUES
(1, '', 'Fashion', '', '/images/user1/fashion/fashion-cat.jpeg'),
(5, '', 'Home Appliances', '', '/images/user1/appliances/appliances-cat.jpeg'),
(6, '', 'Jewellery', '', '/images/user1/jewellery/jew-cat.jpeg'),
(7, '', 'Electronics', '', '/images/user1/electronics/electronics-cat.jpeg'),
(8, '', 'Greeting Cards', '', '/images/user1/cards/greeting-card-cat.jpeg'),
(9, '', 'Food', '', '/images/user1/food/food-cat.png');

-- --------------------------------------------------------

--
-- Table structure for table `Products`
--

CREATE TABLE `Products` (
  `ID` bigint(20) NOT NULL,
  `cost` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `IsEnabled` tinyint(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `categoryName` varchar(255) DEFAULT NULL,
  `productType` varchar(255) DEFAULT NULL,
  `supplierCode` varchar(255) DEFAULT NULL,
  `verId` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `rank` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Products`
--

INSERT INTO `Products` (`ID`, `cost`, `description`, `IsEnabled`, `name`, `url`, `categoryName`, `productType`, `supplierCode`, `verId`, `title`, `rank`) VALUES
(33, 200, 'test desc', 1, 'test-name', '/images/user1/jewellery/jew-1.jpeg', 'Jewellery', NULL, NULL, 2, 'test-title', 1),
(34, 250, 'test-desc', 1, 'test-name', '/images/user1/jewellery/jew-2.jpeg', 'Jewellery', NULL, NULL, 2, 'test-title', 2),
(35, 250, 'test-desc', 1, 'test-name', '/images/user1/jewellery/jew-3.jpeg', 'Jewellery', NULL, NULL, 2, 'test-title', 3),
(36, 300, 'test-desc', 1, 'test-name', '/images/user1/jewellery/jew-4.jpeg', 'Jewellery', NULL, NULL, 2, 'test-title', 4),
(37, 400, 'test-desc', 1, 'test-name', '/images/user1/jewellery/jew-5.jpeg', 'Jewellery', NULL, NULL, 2, 'test-title', 5),
(38, 450, 'test-desc', 1, 'test-name', '/images/user1/jewellery/jew-6.jpeg', 'Jewellery', NULL, NULL, 2, 'test-title', 6),
(39, 500, 'test-desc', 1, 'test-name', '/images/user1/jewellery/jew-7.jpeg', 'Jewellery', NULL, NULL, 2, 'test-title', 7),
(40, 550, 'test-desc', 1, 'test-name', '/images/user1/jewellery/jew-8.jpeg', 'Jewellery', NULL, NULL, 2, 'test-title', 8),
(41, 650, 'test-desc', 1, 'test-name', '/images/user1/jewellery/jew-9.jpeg', 'Jewellery', NULL, NULL, 2, 'test-title', 9),
(42, 750, 'test-desc', 1, 'test-name', '/images/user1/jewellery/jew-10.jpeg', 'Jewellery', NULL, NULL, 2, 'test-title', 10),
(43, 250, 'test-desc', 1, 'test-name', '/images/user1/fashion/ladies-1.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 1),
(44, 350, 'test-desc', 1, 'test-name', '/images/user1/fashion/ladies-2.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 2),
(45, 450, 'test-desc', 1, 'test-name', '/images/user1/fashion/ladies-3.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 3),
(46, 550, 'test-desc', 1, 'test-name', '/images/user1/fashion/ladies-4.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 4),
(47, 650, 'test-desc', 1, 'test-name', '/images/user1/fashion/ladies-5.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 5),
(48, 350, 'test-desc', 1, 'test-name', '/images/user1/fashion/ladies-6.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 6),
(49, 250, 'test-desc', 1, 'test-name', '/images/user1/fashion/ladies-7.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 7),
(50, 550, 'test-desc', 1, 'test-name', '/images/user1/fashion/ladies-8.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 8),
(51, 450, 'test-desc', 1, 'test-name', '/images/user1/fashion/ladies-9.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 9),
(52, 750, 'test-desc', 1, 'test-name', '/images/user1/fashion/ladies-10.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 10),
(53, 850, 'test-desc', 1, 'test-name', '/images/user1/fashion/baby-1.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 11),
(54, 950, 'test-desc', 1, 'test-name', '/images/user1/fashion/baby-2.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 12),
(55, 250, 'test-desc', 1, 'test-name', '/images/user1/fashion/baby-3.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 13),
(56, 350, 'test-desc', 1, 'test-name', '/images/user1/fashion/baby-4.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 14),
(57, 450, 'test-desc', 1, 'test-name', '/images/user1/fashion/mens-1.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 15),
(58, 550, 'test-desc', 1, 'test-name', '/images/user1/fashion/mens-2.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 16),
(59, 650, 'test-desc', 1, 'test-name', '/images/user1/fashion/mens-3.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 17),
(60, 750, 'test-desc', 1, 'test-name', '/images/user1/fashion/mens-4.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 18),
(61, 150, 'test-desc', 1, 'test-name', '/images/user1/fashion/mens-5.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 19),
(62, 250, 'test-desc', 1, 'test-name', '/images/user1/fashion/mens-6.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 20),
(63, 350, 'test-desc', 1, 'test-name', '/images/user1/fashion/mens-7.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 21),
(64, 450, 'test-desc', 1, 'test-name', '/images/user1/fashion/mens-8.jpeg', 'Fashion', NULL, NULL, 2, 'test-title', 22),
(65, 750, 'test-desc', 1, 'test-name', '/images/user1/electronics/speakers.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 1),
(66, 650, 'test-desc', 1, 'test-name', '/images/user1/electronics/Tablet-1.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 2),
(67, 200, 'test-desc', 1, 'test-name', '/images/user1/electronics/Tablet-2.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 3),
(68, 300, 'test-desc', 1, 'test-name', '/images/user1/electronics/camera.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 4),
(69, 400, 'test-desc', 1, 'test-name', '/images/user1/electronics/camera-2.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 5),
(70, 250, 'test-desc', 1, 'test-name', '/images/user1/electronics/laptop-1.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 6),
(71, 450, 'test-desc', 1, 'test-name', '/images/user1/electronics/laptop-2.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 7),
(72, 350, 'test-desc', 1, 'test-name', '/images/user1/electronics/laptop-3.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 8),
(73, 650, 'test-desc', 1, 'test-name', '/images/user1/electronics/mobile-1.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 9),
(74, 750, 'test-desc', 1, 'test-name', '/images/user1/electronics/mobile-2.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 10),
(75, 750, 'test-desc', 1, 'test-name', '/images/user1/electronics/mobile-3.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 11),
(76, 150, 'test-desc', 1, 'test-name', '/images/user1/electronics/tv-1.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 12),
(77, 250, 'test-desc', 1, 'test-name', '/images/user1/electronics/tv-2.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 113),
(78, 350, 'test-desc', 1, 'test-name', '/images/user1/electronics/tv-3.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 14),
(79, 450, 'test-desc', 1, 'test-name', '/images/user1/electronics/tv-4.jpeg', 'Electronics', NULL, NULL, 2, 'test-title', 15),
(80, 5000, 'test-desc', 1, 'test-name', '/images/user1/appliances/windows-ac.png', 'Home Appliances', NULL, NULL, 2, 'test-title', 1),
(81, 6000, 'test-desc', 1, 'test-name', '/images/user1/appliances/split-ac.jpeg', 'Home Appliances', NULL, NULL, 2, 'test-title', 2),
(82, 7000, 'test-desc', 1, 'test-name', '/images/user1/appliances/vaccum-cleaner.jpeg', 'Home Appliances', NULL, NULL, 2, 'test-title', 3),
(83, 8000, 'test-desc', 1, 'test-name', '/images/user1/appliances/washing-machine.jpeg', 'Home Appliances', NULL, NULL, 2, 'test-title', 4),
(84, 9000, 'test-desc', 1, 'test-name', '/images/user1/appliances/water-purifier.jpeg', 'Home Appliances', NULL, NULL, 2, 'test-title', 5),
(85, 3000, 'test-desc', 1, 'test-name', '/images/user1/appliances/mixer-1.jpeg', 'Home Appliances', NULL, NULL, 2, 'test-title', 6),
(86, 4000, 'test-desc', 1, 'test-name', '/images/user1/appliances/mixer-2.jpeg', 'Home Appliances', NULL, NULL, 2, 'test-title', 7),
(87, 7000, 'test-desc', 1, 'test-name', '/images/user1/appliances/sewing-machine-1.jpeg', 'Home Appliances', NULL, NULL, 2, 'test-title', 8),
(88, 9000, 'test-desc', 1, 'test-name', '/images/user1/appliances/sewing-machine-2.jpeg', 'Home Appliances', NULL, NULL, 2, 'test-title', 9),
(89, 15000, 'test-desc', 1, 'test-name', '/images/user1/appliances/refrigerator.jpeg', 'Home Appliances', NULL, NULL, 2, 'test-title', 10),
(90, 50, 'test-desc', 1, 'test-name', '/images/user1/cards/greeting-card-1.jpeg', 'Greeting Cards', NULL, NULL, 2, 'test-title', 1),
(91, 100, 'test-desc', 1, 'test-name', '/images/user1/cards/greeting-card-2.jpeg', 'Greeting Cards', NULL, NULL, 2, 'test-title', 2),
(92, 100, 'test-desc', 1, 'test-name', '/images/user1/cards/greeting-card-3.jpeg', 'Greeting Cards', NULL, NULL, 2, 'test-title', 3),
(93, 150, 'test-desc', 1, 'test-name', '/images/user1/cards/greeting-card-4.jpeg', 'Greeting Cards', NULL, NULL, 2, 'test-title', 4),
(94, 200, 'test-desc', 1, 'test-name', '/images/user1/cards/greeting-card-5.jpeg', 'Greeting Cards', NULL, NULL, 2, 'test-title', 5),
(95, 50, 'test-desc', 1, 'test-name', '/images/user1/cards/greeting-card-6.jpeg', 'Greeting Cards', NULL, NULL, 2, 'test-title', 6),
(96, 100, 'test-desc', 1, 'test-name', '/images/user1/cards/greeting-card-7.jpeg', 'Greeting Cards', NULL, NULL, 2, 'test-title', 7),
(97, 150, 'test-desc', 1, 'test-name', '/images/user1/cards/greeting-card-8.jpeg', 'Greeting Cards', NULL, NULL, 2, 'test-title', 8),
(98, 100, 'test-desc', 1, 'test-name', '/images/user1/cards/greeting-card-9.jpeg', 'Greeting Cards', NULL, NULL, 2, 'test-title', 9),
(99, 150, 'test-desc', 1, 'test-name', '/images/user1/cards/greeting-card-10.jpeg', 'Greeting Cards', NULL, NULL, 2, 'test-title', 10),
(100, 100, 'test-desc', 1, 'test-name', '/images/user1/food/chinese.jpg', 'Food', NULL, NULL, 2, 'test-title', 1),
(101, 200, 'test-desc', 1, 'test-name', '/images/user1/food/food1.jpg', 'Food', NULL, NULL, 2, 'test-title', 2),
(102, 300, 'test-desc', 1, 'test-name', '/images/user1/food/food3.png', 'Food', NULL, NULL, 2, 'test-title', 3),
(103, 200, 'test-desc', 1, 'test-name', '/images/user1/food/kabab.jpg', 'Food', NULL, NULL, 2, 'test-title', 4),
(104, 50, 'test-desc', 1, 'test-name', '/images/user1/food/masalapapad.jpg', 'Food', NULL, NULL, 2, 'test-title', 5);

-- --------------------------------------------------------

--
-- Table structure for table `ProductVersion`
--

CREATE TABLE `ProductVersion` (
  `ID` bigint(20) NOT NULL,
  `version` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ProductVersion`
--

INSERT INTO `ProductVersion` (`ID`, `version`) VALUES
(1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `RefAddressTypes`
--

CREATE TABLE `RefAddressTypes` (
  `ID` bigint(20) NOT NULL,
  `addressTypeCode` varchar(255) DEFAULT NULL,
  `addressTypeDescr` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RefAddressTypes`
--

INSERT INTO `RefAddressTypes` (`ID`, `addressTypeCode`, `addressTypeDescr`) VALUES
(1, 'Home', 'Home Description'),
(2, 'Delivery', 'Delivery Descr');

-- --------------------------------------------------------

--
-- Table structure for table `RefDeliveryStatusCodes`
--

CREATE TABLE `RefDeliveryStatusCodes` (
  `deliveryStatusCodes` varchar(255) NOT NULL,
  `deliveryStatusDescr` varchar(255) DEFAULT NULL,
  `ID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RefDeliveryStatusCodes`
--

INSERT INTO `RefDeliveryStatusCodes` (`deliveryStatusCodes`, `deliveryStatusDescr`, `ID`) VALUES
('Delivered', 'Delivered Descr', 0),
('NotDelivered', 'NotDelivered Descr', 0);

-- --------------------------------------------------------

--
-- Table structure for table `RefOrderStatusCodes`
--

CREATE TABLE `RefOrderStatusCodes` (
  `ID` bigint(20) NOT NULL,
  `orderStatusCode` varchar(255) DEFAULT NULL,
  `orderStatusDescr` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RefOrderStatusCodes`
--

INSERT INTO `RefOrderStatusCodes` (`ID`, `orderStatusCode`, `orderStatusDescr`) VALUES
(1, 'Paid', 'Paid Descr'),
(2, 'Notpaid', 'Notpaid descr'),
(3, 'Partfilled', 'Partfilled Descr');

-- --------------------------------------------------------

--
-- Table structure for table `RefPaymentMethods`
--

CREATE TABLE `RefPaymentMethods` (
  `ID` bigint(20) NOT NULL,
  `paymentMethodCode` varchar(255) NOT NULL,
  `paymentMethodDescription` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RefPaymentMethods`
--

INSERT INTO `RefPaymentMethods` (`ID`, `paymentMethodCode`, `paymentMethodDescription`) VALUES
(1, 'COD', 'COD Desc');

-- --------------------------------------------------------

--
-- Table structure for table `RefProductTypes`
--

CREATE TABLE `RefProductTypes` (
  `ID` bigint(20) NOT NULL,
  `levelNumber` int(11) NOT NULL,
  `productTypeCode` varchar(255) NOT NULL,
  `productTypeDescription` varchar(255) DEFAULT NULL,
  `parentProductTypeCode_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Suppliers`
--

CREATE TABLE `Suppliers` (
  `ID` bigint(20) NOT NULL,
  `otherSuppllierDetails` varchar(255) DEFAULT NULL,
  `supplierCode` varchar(255) NOT NULL,
  `supplierName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `TestTBD`
--

CREATE TABLE `TestTBD` (
  `ID` bigint(20) NOT NULL,
  `Url` varchar(255) DEFAULT NULL,
  `products_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `URLS`
--

CREATE TABLE `URLS` (
  `ID` bigint(20) NOT NULL,
  `Url` varchar(255) DEFAULT NULL,
  `products_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `URLS`
--

INSERT INTO `URLS` (`ID`, `Url`, `products_ID`) VALUES
(42, '/images/user1/jewellery/jew-1.jpeg', 33),
(43, '/images/user1/jewellery/jew-2.jpeg', 34),
(44, '/images/user1/jewellery/jew-3.jpeg', 35),
(45, '/images/user1/jewellery/jew-4.jpeg', 36),
(46, '/images/user1/jewellery/jew-5.jpeg', 37),
(47, '/images/user1/jewellery/jew-6.jpeg', 38),
(48, '/images/user1/jewellery/jew-7.jpeg', 39),
(49, '/images/user1/jewellery/jew-8.jpeg', 40),
(50, '/images/user1/jewellery/jew-9.jpeg', 41),
(51, '/images/user1/jewellery/jew-10.jpeg', 42),
(52, '/images/user1/jewellery/jew-1.jpeg', 42),
(53, '/images/user1/jewellery/jew-2.jpeg', 33),
(54, '/images/user1/jewellery/jew-3.jpeg', 34),
(55, '/images/user1/jewellery/jew-4.jpeg', 35),
(56, '/images/user1/jewellery/jew-5.jpeg', 36),
(57, '/images/user1/jewellery/jew-6.jpeg', 37),
(58, '/images/user1/jewellery/jew-7.jpeg', 38),
(59, '/images/user1/jewellery/jew-8.jpeg', 39),
(60, '/images/user1/jewellery/jew-9.jpeg', 40),
(61, '/images/user1/jewellery/jew-10.jpeg', 41),
(62, '/images/user1/jewellery/jew-1.jpeg', 41),
(63, '/images/user1/jewellery/jew-2.jpeg', 42),
(64, '/images/user1/jewellery/jew-3.jpeg', 33),
(65, '/images/user1/jewellery/jew-4.jpeg', 34),
(66, '/images/user1/jewellery/jew-5.jpeg', 35),
(67, '/images/user1/jewellery/jew-6.jpeg', 36),
(68, '/images/user1/jewellery/jew-7.jpeg', 37),
(69, '/images/user1/jewellery/jew-8.jpeg', 38),
(70, '/images/user1/jewellery/jew-9.jpeg', 39),
(71, '/images/user1/jewellery/jew-10.jpeg', 40),
(72, '/images/user1/fashion/ladies-1.jpeg', 43),
(73, '/images/user1/fashion/ladies-2.jpeg', 44),
(74, '/images/user1/fashion/ladies-3.jpeg', 45),
(75, '/images/user1/fashion/ladies-4.jpeg', 46),
(76, '/images/user1/fashion/ladies-5.jpeg', 47),
(77, '/images/user1/fashion/ladies-6.jpeg', 48),
(78, '/images/user1/fashion/ladies-7.jpeg', 49),
(79, '/images/user1/fashion/ladies-8.jpeg', 50),
(80, '/images/user1/fashion/ladies-9.jpeg', 51),
(81, '/images/user1/fashion/ladies-10.jpeg', 52),
(82, '/images/user1/fashion/ladies-1.jpeg', 52),
(83, '/images/user1/fashion/ladies-2.jpeg', 43),
(84, '/images/user1/fashion/ladies-3.jpeg', 44),
(85, '/images/user1/fashion/ladies-4.jpeg', 45),
(86, '/images/user1/fashion/ladies-5.jpeg', 46),
(87, '/images/user1/fashion/ladies-6.jpeg', 47),
(88, '/images/user1/fashion/ladies-7.jpeg', 48),
(89, '/images/user1/fashion/ladies-8.jpeg', 49),
(90, '/images/user1/fashion/ladies-9.jpeg', 50),
(91, '/images/user1/fashion/ladies-10.jpeg', 51),
(92, '/images/user1/fashion/ladies-1.jpeg', 51),
(93, '/images/user1/fashion/ladies-2.jpeg', 52),
(94, '/images/user1/fashion/ladies-3.jpeg', 43),
(95, '/images/user1/fashion/ladies-4.jpeg', 44),
(96, '/images/user1/fashion/ladies-5.jpeg', 45),
(97, '/images/user1/fashion/ladies-6.jpeg', 46),
(98, '/images/user1/fashion/ladies-7.jpeg', 47),
(99, '/images/user1/fashion/ladies-8.jpeg', 48),
(100, '/images/user1/fashion/ladies-9.jpeg', 49),
(101, '/images/user1/fashion/ladies-10.jpeg', 50),
(102, '/images/user1/fashion/baby-1.jpeg', 53),
(103, '/images/user1/fashion/baby-2.jpeg', 54),
(104, '/images/user1/fashion/baby-3.jpeg', 55),
(105, '/images/user1/fashion/baby-4.jpeg', 56),
(106, '/images/user1/fashion/baby-1.jpeg', 56),
(107, '/images/user1/fashion/baby-2.jpeg', 53),
(108, '/images/user1/fashion/baby-3.jpeg', 54),
(109, '/images/user1/fashion/baby-4.jpeg', 55),
(110, '/images/user1/fashion/baby-1.jpeg', 55),
(111, '/images/user1/fashion/baby-2.jpeg', 56),
(112, '/images/user1/fashion/baby-3.jpeg', 53),
(113, '/images/user1/fashion/baby-4.jpeg', 54),
(114, '/images/user1/fashion/mens-1.jpeg', 57),
(115, '/images/user1/fashion/mens-2.jpeg', 58),
(116, '/images/user1/fashion/mens-3.jpeg', 59),
(117, '/images/user1/fashion/mens-4.jpeg', 60),
(118, '/images/user1/fashion/mens-5.jpeg', 61),
(119, '/images/user1/fashion/mens-6.jpeg', 62),
(120, '/images/user1/fashion/mens-7.jpeg', 63),
(121, '/images/user1/fashion/mens-8.jpeg', 64),
(122, '/images/user1/fashion/mens-1.jpeg', 64),
(123, '/images/user1/fashion/mens-2.jpeg', 57),
(124, '/images/user1/fashion/mens-3.jpeg', 58),
(125, '/images/user1/fashion/mens-4.jpeg', 59),
(126, '/images/user1/fashion/mens-5.jpeg', 60),
(127, '/images/user1/fashion/mens-6.jpeg', 61),
(128, '/images/user1/fashion/mens-7.jpeg', 62),
(129, '/images/user1/fashion/mens-8.jpeg', 63),
(130, '/images/user1/fashion/mens-1.jpeg', 63),
(131, '/images/user1/fashion/mens-2.jpeg', 64),
(132, '/images/user1/fashion/mens-3.jpeg', 57),
(133, '/images/user1/fashion/mens-4.jpeg', 58),
(134, '/images/user1/fashion/mens-5.jpeg', 59),
(135, '/images/user1/fashion/mens-6.jpeg', 60),
(136, '/images/user1/fashion/mens-7.jpeg', 61),
(137, '/images/user1/fashion/mens-8.jpeg', 62),
(138, '/images/user1/electronics/speakers.jpeg', 65),
(139, '/images/user1/electronics/Tablet-1.jpeg', 66),
(140, '/images/user1/electronics/Tablet-2.jpeg', 67),
(141, '/images/user1/electronics/camera.jpeg', 68),
(142, '/images/user1/electronics/camera-2.jpeg', 69),
(143, '/images/user1/electronics/laptop-1.jpeg', 70),
(144, '/images/user1/electronics/laptop-2.jpeg', 71),
(145, '/images/user1/electronics/laptop-3.jpeg', 72),
(146, '/images/user1/electronics/mobile-1.jpeg', 73),
(147, '/images/user1/electronics/mobile-2.jpeg', 74),
(148, '/images/user1/electronics/mobile-3.jpeg', 75),
(149, '/images/user1/electronics/tv-1.jpeg', 76),
(150, '/images/user1/electronics/tv-2.jpeg', 77),
(151, '/images/user1/electronics/tv-3.jpeg', 78),
(152, '/images/user1/electronics/tv-4.jpeg', 79),
(153, '/images/user1/electronics/speakers.jpeg', 66),
(154, '/images/user1/electronics/Tablet-1.jpeg', 67),
(155, '/images/user1/electronics/Tablet-2.jpeg', 68),
(156, '/images/user1/electronics/camera.jpeg', 69),
(157, '/images/user1/electronics/camera-2.jpeg', 70),
(158, '/images/user1/electronics/laptop-1.jpeg', 71),
(159, '/images/user1/electronics/laptop-2.jpeg', 72),
(160, '/images/user1/electronics/laptop-3.jpeg', 73),
(161, '/images/user1/electronics/mobile-1.jpeg', 74),
(162, '/images/user1/electronics/mobile-2.jpeg', 75),
(163, '/images/user1/electronics/mobile-3.jpeg', 76),
(164, '/images/user1/electronics/tv-1.jpeg', 77),
(165, '/images/user1/electronics/tv-2.jpeg', 78),
(166, '/images/user1/electronics/tv-3.jpeg', 79),
(167, '/images/user1/electronics/tv-4.jpeg', 65),
(168, '/images/user1/electronics/speakers.jpeg', 67),
(169, '/images/user1/electronics/Tablet-1.jpeg', 68),
(170, '/images/user1/electronics/Tablet-2.jpeg', 69),
(171, '/images/user1/electronics/camera.jpeg', 70),
(172, '/images/user1/electronics/camera-2.jpeg', 71),
(173, '/images/user1/electronics/laptop-1.jpeg', 72),
(174, '/images/user1/electronics/laptop-2.jpeg', 73),
(175, '/images/user1/electronics/laptop-3.jpeg', 74),
(176, '/images/user1/electronics/mobile-1.jpeg', 75),
(177, '/images/user1/electronics/mobile-2.jpeg', 76),
(178, '/images/user1/electronics/mobile-3.jpeg', 77),
(179, '/images/user1/electronics/tv-1.jpeg', 78),
(180, '/images/user1/electronics/tv-2.jpeg', 79),
(181, '/images/user1/electronics/tv-3.jpeg', 65),
(182, '/images/user1/electronics/tv-4.jpeg', 66),
(183, '/images/user1/appliances/windows-ac.png', 80),
(184, '/images/user1/appliances/split-ac.jpeg', 81),
(185, '/images/user1/appliances/vaccum-cleaner.jpeg', 82),
(186, '/images/user1/appliances/washing-machine', 83),
(187, '/images/user1/appliances/water-purifier.jpeg', 84),
(188, '/images/user1/appliances/mixer-1.jpeg', 85),
(189, '/images/user1/appliances/mixer-2.jpeg', 86),
(190, '/images/user1/appliances/sewing-machine-1.jpeg', 87),
(191, '/images/user1/appliances/sewing-machine-2.jpeg', 88),
(192, '/images/user1/appliances/refrigerator.jpeg', 89),
(193, '/images/user1/appliances/windows-ac.png', 80),
(194, '/images/user1/appliances/split-ac.jpeg', 81),
(195, '/images/user1/appliances/vaccum-cleaner.jpeg', 82),
(196, '/images/user1/appliances/washing-machine', 83),
(197, '/images/user1/appliances/water-purifier.jpeg', 84),
(198, '/images/user1/appliances/mixer-1.jpeg', 86),
(199, '/images/user1/appliances/mixer-2.jpeg', 85),
(200, '/images/user1/appliances/sewing-machine-1.jpeg', 88),
(201, '/images/user1/appliances/sewing-machine-2.jpeg', 87),
(202, '/images/user1/appliances/refrigerator.jpeg', 89),
(203, '/images/user1/cards/greeting-card-1.jpeg', 90),
(204, '/images/user1/cards/greeting-card-2.jpeg', 91),
(205, '/images/user1/cards/greeting-card-3.jpeg', 92),
(206, '/images/user1/cards/greeting-card-4.jpeg', 93),
(207, '/images/user1/cards/greeting-card-5.jpeg', 94),
(208, '/images/user1/cards/greeting-card-6.jpeg', 95),
(209, '/images/user1/cards/greeting-card-7.jpeg', 96),
(210, '/images/user1/cards/greeting-card-8.jpeg', 97),
(211, '/images/user1/cards/greeting-card-9.jpeg', 98),
(212, '/images/user1/cards/greeting-card-10.jpeg', 99),
(213, '/images/user1/cards/greeting-card-1.jpeg', 91),
(214, '/images/user1/cards/greeting-card-2.jpeg', 92),
(215, '/images/user1/cards/greeting-card-3.jpeg', 93),
(216, '/images/user1/cards/greeting-card-4.jpeg', 94),
(217, '/images/user1/cards/greeting-card-5.jpeg', 95),
(218, '/images/user1/cards/greeting-card-6.jpeg', 96),
(219, '/images/user1/cards/greeting-card-7.jpeg', 97),
(220, '/images/user1/cards/greeting-card-8.jpeg', 98),
(221, '/images/user1/cards/greeting-card-9.jpeg', 99),
(222, '/images/user1/cards/greeting-card-10.jpeg', 90),
(223, '/images/user1/cards/greeting-card-1.jpeg', 92),
(224, '/images/user1/cards/greeting-card-2.jpeg', 93),
(225, '/images/user1/cards/greeting-card-3.jpeg', 94),
(226, '/images/user1/cards/greeting-card-4.jpeg', 95),
(227, '/images/user1/cards/greeting-card-5.jpeg', 96),
(228, '/images/user1/cards/greeting-card-6.jpeg', 97),
(229, '/images/user1/cards/greeting-card-7.jpeg', 98),
(230, '/images/user1/cards/greeting-card-8.jpeg', 99),
(231, '/images/user1/cards/greeting-card-9.jpeg', 90),
(232, '/images/user1/cards/greeting-card-10.jpeg', 91),
(233, '/images/user1/cards/greeting-card-1.jpeg', 93),
(234, '/images/user1/cards/greeting-card-2.jpeg', 94),
(235, '/images/user1/cards/greeting-card-3.jpeg', 95),
(236, '/images/user1/cards/greeting-card-4.jpeg', 96),
(237, '/images/user1/cards/greeting-card-5.jpeg', 97),
(238, '/images/user1/cards/greeting-card-6.jpeg', 98),
(239, '/images/user1/cards/greeting-card-7.jpeg', 99),
(240, '/images/user1/cards/greeting-card-8.jpeg', 90),
(241, '/images/user1/cards/greeting-card-9.jpeg', 91),
(242, '/images/user1/cards/greeting-card-10.jpeg', 92),
(243, '/images/user1/cards/greeting-card-1.jpeg', 96),
(244, '/images/user1/cards/greeting-card-2.jpeg', 97),
(245, '/images/user1/cards/greeting-card-3.jpeg', 98),
(246, '/images/user1/cards/greeting-card-4.jpeg', 99),
(247, '/images/user1/cards/greeting-card-5.jpeg', 90),
(248, '/images/user1/cards/greeting-card-6.jpeg', 91),
(249, '/images/user1/cards/greeting-card-7.jpeg', 92),
(250, '/images/user1/cards/greeting-card-8.jpeg', 93),
(251, '/images/user1/cards/greeting-card-9.jpeg', 94),
(252, '/images/user1/cards/greeting-card-10.jpeg', 95),
(253, '/images/user1/food/chinese.jpg', 100),
(254, '/images/user1/food/food1.jpg', 101),
(255, '/images/user1/food/food3.png', 102),
(256, '/images/user1/food/kabab.jpg', 103),
(257, '/images/user1/food/masalapapad.jpg', 104),
(258, '/images/user1/food/chinese.jpg', 101),
(259, '/images/user1/food/food1.jpg', 102),
(260, '/images/user1/food/food3.png', 103),
(261, '/images/user1/food/kabab.jpg', 104),
(262, '/images/user1/food/masalapapad.jpg', 100),
(263, '/images/user1/food/food3.png', 101),
(264, '/images/user1/food/masalapapad.jpg', 102),
(265, '/images/user1/food/chinese.jpg', 103),
(266, '/images/user1/food/food3.png', 103),
(267, '/images/user1/food/kabab.jpg', 101),
(268, '/images/user1/food/masalapapad.jpg', 101);

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `ID` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `saltedHash` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Address`
--
ALTER TABLE `Address`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `Banners`
--
ALTER TABLE `Banners`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `CustomerAddresses`
--
ALTER TABLE `CustomerAddresses`
  ADD PRIMARY KEY (`addr_ID`,`cust_ID`),
  ADD KEY `FK_gcmho60qwmcq7fbgi63rt1ff9` (`cust_ID`),
  ADD KEY `FK_5mn73c47a7p709xtgdclvt0sr` (`addressType`);

--
-- Indexes for table `CustomerOrderProducts`
--
ALTER TABLE `CustomerOrderProducts`
  ADD PRIMARY KEY (`customerOrders_ID`,`products_ID`),
  ADD KEY `FK_g7dpdc4jqlmistdids0brciw2` (`products_ID`);

--
-- Indexes for table `CustomerOrders`
--
ALTER TABLE `CustomerOrders`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_eil5351ihwk6naexx2w828vux` (`orderStatus`),
  ADD KEY `FK_3bpl3jnsjph5sik2wsoarne76` (`cust_ID`),
  ADD KEY `FK_3hdcih26x9tt4njhq02dc7p8n` (`payment_ID`),
  ADD KEY `FK4u85gk5ecx7oyq0j8mripp3xx` (`deliveryAddress_ID`);

--
-- Indexes for table `CustomerOrdersDelivery`
--
ALTER TABLE `CustomerOrdersDelivery`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_hpbt42yh679lxtx7gd33ey8sy` (`orders_ID`),
  ADD KEY `FK_scxvllxeu5de03abj6ftv817c` (`deliveryStatus`);

--
-- Indexes for table `CustomerPaymentMethods`
--
ALTER TABLE `CustomerPaymentMethods`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_h8jc0blyv7p7t02s4ckmw1stn` (`customer_ID`),
  ADD KEY `FK_hds2u73yf6w26quy81yjspvll` (`paymentMethods_ID`),
  ADD KEY `FKd15g7dxmyf1d6bxlwgpqpfd2a` (`orders_ID`);

--
-- Indexes for table `Customers`
--
ALTER TABLE `Customers`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `ElectronicContact`
--
ALTER TABLE `ElectronicContact`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_1il3cpkgneuuq2wxpkr99e1g2` (`email`),
  ADD UNIQUE KEY `UK_bmkfbnintqinuoq0ab11gk008` (`phone`),
  ADD KEY `FK_9helsjns64gc8dcsjmgo82yyh` (`cust_ID`);

--
-- Indexes for table `EmailVerification`
--
ALTER TABLE `EmailVerification`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_o9mkel0xfjhkj7ugq8xf9wk6m` (`email`);

--
-- Indexes for table `ProductCategories`
--
ALTER TABLE `ProductCategories`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_rjq0645vf3us3yqheom1u3y01` (`name`);

--
-- Indexes for table `Products`
--
ALTER TABLE `Products`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_oted4uap5lbjemkkyijefuc6i` (`categoryName`),
  ADD KEY `FK_1xw0rbwu4i6satol7dlm5fvhv` (`productType`),
  ADD KEY `FK_ms5w18tgieu6oqcimhef894om` (`supplierCode`),
  ADD KEY `FK_qs3e8pyexk7a42qd1bmfgjlrj` (`verId`);

--
-- Indexes for table `ProductVersion`
--
ALTER TABLE `ProductVersion`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_51tg6os6f4nxkiel40w88gmfe` (`version`);

--
-- Indexes for table `RefAddressTypes`
--
ALTER TABLE `RefAddressTypes`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_25emp1vvbp0gpp16q8sm03m22` (`addressTypeCode`);

--
-- Indexes for table `RefDeliveryStatusCodes`
--
ALTER TABLE `RefDeliveryStatusCodes`
  ADD PRIMARY KEY (`deliveryStatusCodes`),
  ADD UNIQUE KEY `UK_bqjwghgyomsc0id9n3reixppo` (`deliveryStatusCodes`);

--
-- Indexes for table `RefOrderStatusCodes`
--
ALTER TABLE `RefOrderStatusCodes`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_f6dfq9651f8c3bg448x14xdqs` (`orderStatusCode`);

--
-- Indexes for table `RefPaymentMethods`
--
ALTER TABLE `RefPaymentMethods`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_yuqwjo0gqnbcq1n6qqjqehgw` (`paymentMethodCode`);

--
-- Indexes for table `RefProductTypes`
--
ALTER TABLE `RefProductTypes`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_tmkqaieqo1bocu0wcr02broj2` (`productTypeCode`),
  ADD KEY `FK_spry2msbalpfees6oxec73np4` (`parentProductTypeCode_ID`);

--
-- Indexes for table `Suppliers`
--
ALTER TABLE `Suppliers`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_hihw9ehu5smlga1qq0c4dr6pa` (`supplierCode`);

--
-- Indexes for table `TestTBD`
--
ALTER TABLE `TestTBD`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_kh781bvye270v2q4b6strw4b7` (`products_ID`);

--
-- Indexes for table `URLS`
--
ALTER TABLE `URLS`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `products_ID` (`products_ID`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `UK_ncoa9bfasrql0x4nhmh1plxxy` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Address`
--
ALTER TABLE `Address`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `Banners`
--
ALTER TABLE `Banners`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `CustomerOrders`
--
ALTER TABLE `CustomerOrders`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `CustomerOrdersDelivery`
--
ALTER TABLE `CustomerOrdersDelivery`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `CustomerPaymentMethods`
--
ALTER TABLE `CustomerPaymentMethods`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Customers`
--
ALTER TABLE `Customers`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `ElectronicContact`
--
ALTER TABLE `ElectronicContact`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `ProductCategories`
--
ALTER TABLE `ProductCategories`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `Products`
--
ALTER TABLE `Products`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;
--
-- AUTO_INCREMENT for table `ProductVersion`
--
ALTER TABLE `ProductVersion`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `RefAddressTypes`
--
ALTER TABLE `RefAddressTypes`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `RefOrderStatusCodes`
--
ALTER TABLE `RefOrderStatusCodes`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `RefPaymentMethods`
--
ALTER TABLE `RefPaymentMethods`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `RefProductTypes`
--
ALTER TABLE `RefProductTypes`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `Suppliers`
--
ALTER TABLE `Suppliers`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `TestTBD`
--
ALTER TABLE `TestTBD`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `URLS`
--
ALTER TABLE `URLS`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=269;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `CustomerAddresses`
--
ALTER TABLE `CustomerAddresses`
  ADD CONSTRAINT `FK_428du54hjmli47470ff150m5m` FOREIGN KEY (`addr_ID`) REFERENCES `Address` (`ID`),
  ADD CONSTRAINT `FK_5mn73c47a7p709xtgdclvt0sr` FOREIGN KEY (`addressType`) REFERENCES `RefAddressTypes` (`addressTypeCode`),
  ADD CONSTRAINT `FK_gcmho60qwmcq7fbgi63rt1ff9` FOREIGN KEY (`cust_ID`) REFERENCES `Customers` (`ID`),
  ADD CONSTRAINT `FKc9rppt20sbwhrt1qm6877gp5j` FOREIGN KEY (`addressType`) REFERENCES `RefAddressTypes` (`addressTypeCode`),
  ADD CONSTRAINT `FKrrhwaa4eikboy5rucx441wscj` FOREIGN KEY (`cust_ID`) REFERENCES `Customers` (`ID`),
  ADD CONSTRAINT `FKsn1nt8x7lamco0h7gjsn15ny1` FOREIGN KEY (`addr_ID`) REFERENCES `Address` (`ID`);

--
-- Constraints for table `CustomerOrderProducts`
--
ALTER TABLE `CustomerOrderProducts`
  ADD CONSTRAINT `FK_g7dpdc4jqlmistdids0brciw2` FOREIGN KEY (`products_ID`) REFERENCES `Products` (`ID`),
  ADD CONSTRAINT `FK_kaqx67ya6hjb0wfe5psu15ghg` FOREIGN KEY (`customerOrders_ID`) REFERENCES `CustomerOrders` (`ID`),
  ADD CONSTRAINT `FKsds745x4pwjqtsiejujdjctd8` FOREIGN KEY (`customerOrders_ID`) REFERENCES `CustomerOrders` (`ID`),
  ADD CONSTRAINT `FKsu123mm4jt3myyxghr2nlxtgs` FOREIGN KEY (`products_ID`) REFERENCES `Products` (`ID`);

--
-- Constraints for table `CustomerOrders`
--
ALTER TABLE `CustomerOrders`
  ADD CONSTRAINT `FK4u85gk5ecx7oyq0j8mripp3xx` FOREIGN KEY (`deliveryAddress_ID`) REFERENCES `Address` (`ID`),
  ADD CONSTRAINT `FK_3bpl3jnsjph5sik2wsoarne76` FOREIGN KEY (`cust_ID`) REFERENCES `Customers` (`ID`),
  ADD CONSTRAINT `FK_3hdcih26x9tt4njhq02dc7p8n` FOREIGN KEY (`payment_ID`) REFERENCES `CustomerPaymentMethods` (`ID`),
  ADD CONSTRAINT `FK_eil5351ihwk6naexx2w828vux` FOREIGN KEY (`orderStatus`) REFERENCES `RefOrderStatusCodes` (`orderStatusCode`),
  ADD CONSTRAINT `FKdige1tn76aj5sjqg4dw0j4mcr` FOREIGN KEY (`orderStatus`) REFERENCES `RefOrderStatusCodes` (`orderStatusCode`),
  ADD CONSTRAINT `FKi04twho85u18io2ofdcjpf8jm` FOREIGN KEY (`cust_ID`) REFERENCES `Customers` (`ID`);

--
-- Constraints for table `CustomerOrdersDelivery`
--
ALTER TABLE `CustomerOrdersDelivery`
  ADD CONSTRAINT `FK1n4ytr5lo99rnu5wcwrgc5x6s` FOREIGN KEY (`orders_ID`) REFERENCES `CustomerOrders` (`ID`),
  ADD CONSTRAINT `FK_hpbt42yh679lxtx7gd33ey8sy` FOREIGN KEY (`orders_ID`) REFERENCES `CustomerOrders` (`ID`),
  ADD CONSTRAINT `FK_scxvllxeu5de03abj6ftv817c` FOREIGN KEY (`deliveryStatus`) REFERENCES `RefDeliveryStatusCodes` (`deliveryStatusCodes`),
  ADD CONSTRAINT `FKf79v8gkujd3v2sc1abuxwqp1h` FOREIGN KEY (`deliveryStatus`) REFERENCES `RefDeliveryStatusCodes` (`deliveryStatusCodes`);

--
-- Constraints for table `CustomerPaymentMethods`
--
ALTER TABLE `CustomerPaymentMethods`
  ADD CONSTRAINT `FK_h8jc0blyv7p7t02s4ckmw1stn` FOREIGN KEY (`customer_ID`) REFERENCES `Customers` (`ID`),
  ADD CONSTRAINT `FK_hds2u73yf6w26quy81yjspvll` FOREIGN KEY (`paymentMethods_ID`) REFERENCES `RefPaymentMethods` (`ID`),
  ADD CONSTRAINT `FKd15g7dxmyf1d6bxlwgpqpfd2a` FOREIGN KEY (`orders_ID`) REFERENCES `CustomerOrders` (`ID`),
  ADD CONSTRAINT `FKdcu71ay9dx4327rcwkpo6lf56` FOREIGN KEY (`customer_ID`) REFERENCES `Customers` (`ID`),
  ADD CONSTRAINT `FKnaql7dtuske2ryhogkhj1cddb` FOREIGN KEY (`paymentMethods_ID`) REFERENCES `RefPaymentMethods` (`ID`);

--
-- Constraints for table `ElectronicContact`
--
ALTER TABLE `ElectronicContact`
  ADD CONSTRAINT `FK_9helsjns64gc8dcsjmgo82yyh` FOREIGN KEY (`cust_ID`) REFERENCES `Customers` (`ID`),
  ADD CONSTRAINT `FKbk1hloqogsymb9qwevoyluec3` FOREIGN KEY (`cust_ID`) REFERENCES `Customers` (`ID`);

--
-- Constraints for table `Products`
--
ALTER TABLE `Products`
  ADD CONSTRAINT `FK1lmilofmo7oyvttqy88x51s2q` FOREIGN KEY (`productType`) REFERENCES `RefProductTypes` (`productTypeCode`),
  ADD CONSTRAINT `FK_1xw0rbwu4i6satol7dlm5fvhv` FOREIGN KEY (`productType`) REFERENCES `RefProductTypes` (`productTypeCode`),
  ADD CONSTRAINT `FK_ms5w18tgieu6oqcimhef894om` FOREIGN KEY (`supplierCode`) REFERENCES `Suppliers` (`supplierCode`),
  ADD CONSTRAINT `FK_oted4uap5lbjemkkyijefuc6i` FOREIGN KEY (`categoryName`) REFERENCES `ProductCategories` (`name`),
  ADD CONSTRAINT `FK_qs3e8pyexk7a42qd1bmfgjlrj` FOREIGN KEY (`verId`) REFERENCES `ProductVersion` (`version`),
  ADD CONSTRAINT `FKc87jv9lqe95oujjx64jbq4tii` FOREIGN KEY (`categoryName`) REFERENCES `ProductCategories` (`name`),
  ADD CONSTRAINT `FKcj79699eda56hdva9oudk7e9o` FOREIGN KEY (`verId`) REFERENCES `ProductVersion` (`version`),
  ADD CONSTRAINT `FKe7mp43s9kp45b658cxr6vtbxk` FOREIGN KEY (`supplierCode`) REFERENCES `Suppliers` (`supplierCode`);

--
-- Constraints for table `RefProductTypes`
--
ALTER TABLE `RefProductTypes`
  ADD CONSTRAINT `FK_spry2msbalpfees6oxec73np4` FOREIGN KEY (`parentProductTypeCode_ID`) REFERENCES `RefProductTypes` (`ID`),
  ADD CONSTRAINT `FKmd3tc9s17t309vncyfv97dm91` FOREIGN KEY (`parentProductTypeCode_ID`) REFERENCES `RefProductTypes` (`ID`);

--
-- Constraints for table `TestTBD`
--
ALTER TABLE `TestTBD`
  ADD CONSTRAINT `FK3wlc79tqmgmfa9lfplsf2s3sf` FOREIGN KEY (`products_ID`) REFERENCES `Products` (`ID`),
  ADD CONSTRAINT `FK_kh781bvye270v2q4b6strw4b7` FOREIGN KEY (`products_ID`) REFERENCES `Products` (`ID`);

--
-- Constraints for table `URLS`
--
ALTER TABLE `URLS`
  ADD CONSTRAINT `FK_srlc5kqydvbtr0hc586ncl2fa` FOREIGN KEY (`products_ID`) REFERENCES `Products` (`ID`),
  ADD CONSTRAINT `FKj3vkc95c2iphbftt35f5exff8` FOREIGN KEY (`products_ID`) REFERENCES `Products` (`ID`),
  ADD CONSTRAINT `URLS_ibfk_1` FOREIGN KEY (`products_ID`) REFERENCES `Products` (`ID`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
