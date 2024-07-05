-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (arm64)
--
-- Host: localhost    Database: cookbook
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient` (
  `recipe_id` int unsigned NOT NULL,
  `name` varchar(80) NOT NULL,
  `quantity` decimal(10,0) DEFAULT NULL,
  `unit` varchar(45) DEFAULT NULL,
  `description` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`recipe_id`,`name`),
  KEY `fk_Recipes_idx` (`recipe_id`),
  CONSTRAINT `fk_ingredient_recipe` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (85,'Bay leaves',2,'pieces','optional'),(85,'Ginger slices',4,'slices',''),(85,'Pork',700,'grams','with skin'),(85,'Soy sauce',2,'spoons',''),(85,'Sugar',4,'spoons','small pieces'),(86,'Pepper',150,'g',''),(86,'Pork belly',250,'g','cut it to slices'),(86,'Salt',2,'g','small slices'),(86,'Soy',5,'g',''),(87,'Chicken wings',500,'g',''),(87,'Coca-Cola',300,'ml',''),(87,'Ketchup',2,'spoons',''),(87,'Oyster sauce',2,'spoons',''),(87,'Soy sauce',2,'spoons',''),(88,'Eggs',120,'g','Break up eggs and stir.'),(88,'Salt',2,'spoons',''),(88,'Tomatoes',800,'g','Remove the stalk.');
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preparationstep`
--

DROP TABLE IF EXISTS `preparationstep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preparationstep` (
  `recipe_id` int unsigned NOT NULL,
  `step` int unsigned NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`recipe_id`,`step`),
  KEY `fk_Recipes_idx` (`recipe_id`),
  CONSTRAINT `fk_preparation_step_recipe` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preparationstep`
--

LOCK TABLES `preparationstep` WRITE;
/*!40000 ALTER TABLE `preparationstep` DISABLE KEYS */;
INSERT INTO `preparationstep` VALUES (85,1,'prepare all the ingredients'),(85,2,'clean the pork'),(85,3,'heat the pan without adding oil'),(85,4,'place the pork belly in the pan'),(85,5,'keep the heat on low and turn each piece over'),(85,6,'put all the prepared sauces'),(85,7,'put the boiled cover the pork'),(85,8,'stir occasionally and finally thiciken the sauce'),(85,9,'hongshaorou is ready!'),(86,1,'Wash the peeled pork belly and cut it into thin slices.'),(86,2,'Marinate with soy sauce. Wash and cut fresh peppers into small slices.'),(86,3,'Pick up the pan again, dry fry the chilies, until the surface is scorched, then put in the cooking oil, stir-fry with high heat.'),(86,4,'Then pour in the just-fried pork belly'),(86,5,'Appropriate seasoning according to personal taste, mix in a little sesame oil, stir well.'),(86,6,'Stir-fried meat with chili is ready!'),(87,1,'In a bowl, combine the Coca-Cola, soy sauce, dark soy sauce, oyster sauce, ketchup, brown sugar, minced garlic, and grated ginger. Mix well to make the marinade.'),(87,2,'Place the chicken wings in a ziplock bag or a bowl, and pour the marinade over them. Make sure the chicken wings are evenly coated. Marinate in the refrigerator for at least 2 hours or overnight for best flavor.'),(87,3,'Heat the vegetable oil in a large skillet or frying pan over medium heat. Remove the chicken wings from the marinade, reserving the marinade for later use.'),(87,4,'Place the chicken wings in the hot skillet and cook until they are browned on both sides, about 5 minutes per side.'),(87,5,'Stir the chicken wings occasionally to make sure they are evenly coated with the sauce.'),(87,6,'Serve the Coca-Cola chicken wings hot as an appetizer or as a main course with steamed rice or noodles.'),(87,7,'Coca-Cola chicken wings is ready!'),(88,1,'Put some oil into the pan, pour the egg mixture after oil heating-up;'),(88,2,'Stir-fry and cut into small pieces by truner; After completion, bring it out and wait for back use.');
/*!40000 ALTER TABLE `preparationstep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `recipe_id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `serveamount` int DEFAULT NULL,
  `preparationTime` int unsigned DEFAULT NULL,
  `cookingTime` int unsigned DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`recipe_id`),
  UNIQUE KEY `name_UNIQUE` (`name`,`recipe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (85,'hongshaorou',1,20,40,'src/images/dishes/1720076093234.png'),(86,'Stir-fried meat with chili',1,30,30,'src/images/dishes/1720076724647.png'),(87,'Coca-Cola chicken wings',1,40,30,'src/images/dishes/1720076975228.png'),(88,'scrambled egg with tomato',1,10,20,'src/images/dishes/1720077197871.png');
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `is_vip` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=96653825 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (18592666,'qweqwe','qwe',0),(23561147,'qwe','qwe',1),(35273678,'dddddd','ddd',0),(49689913,'hcyhcyhcyhcyhcy','password123',0),(72386490,'hcy123','password123',0),(78135363,'hcyhcyhcy','password123',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-05 14:28:27
