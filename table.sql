-- 테이블 생성
-- warehouseproject.`member` definition

CREATE TABLE `member` (
  `MEMBER_IDX` int NOT NULL AUTO_INCREMENT,
  `JOINED_DATE` date NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `TEL` varchar(20) NOT NULL,
  `ADDRESS` varchar(100) NOT NULL,
  PRIMARY KEY (`MEMBER_IDX`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- warehouseproject.product definition

CREATE TABLE `product` (
  `PROD_CODE` varchar(10) NOT NULL,
  `CATEGORY` varchar(10) NOT NULL,
  `PROD_NAME` varchar(20) NOT NULL,
  `PROD_SIZE` varchar(5) DEFAULT NULL,
  `PROD_COLOR` varchar(10) DEFAULT NULL,
  `PROD_STOCK` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`PROD_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- warehouseproject.stored_log definition

CREATE TABLE `stored_log` (
  `STORED_IDX` int NOT NULL AUTO_INCREMENT,
  `PROD_CODE` varchar(10) NOT NULL,
  `MEMBER_IDX` int NOT NULL,
  `STORED_DATE` datetime NOT NULL,
  `STORED_STOCK` int NOT NULL,
  PRIMARY KEY (`STORED_IDX`),
  KEY `stored_log_FK` (`PROD_CODE`),
  KEY `stored_log_FK_1` (`MEMBER_IDX`),
  CONSTRAINT `stored_log_FK` FOREIGN KEY (`PROD_CODE`) REFERENCES `product` (`PROD_CODE`) ON UPDATE CASCADE,
  CONSTRAINT `stored_log_FK_1` FOREIGN KEY (`MEMBER_IDX`) REFERENCES `member` (`MEMBER_IDX`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- warehouseproject.takeout_log definition

CREATE TABLE `takeout_log` (
  `TAKEOUT_IDX` int NOT NULL AUTO_INCREMENT,
  `PROD_CODE` varchar(10) NOT NULL,
  `MEMBER_IDX` int NOT NULL,
  `TAKEOUT_DATE` datetime NOT NULL,
  `TAKEOUT_AMOUNT` int NOT NULL,
  `OTHER` varchar(250) DEFAULT NULL,
  `TAKEOUT_ADDRESS` varchar(150) NOT NULL,
  PRIMARY KEY (`TAKEOUT_IDX`),
  KEY `takeout_log_FK` (`PROD_CODE`),
  KEY `takeout_log_FK_1` (`MEMBER_IDX`),
  CONSTRAINT `takeout_log_FK` FOREIGN KEY (`PROD_CODE`) REFERENCES `product` (`PROD_CODE`) ON UPDATE CASCADE,
  CONSTRAINT `takeout_log_FK_1` FOREIGN KEY (`MEMBER_IDX`) REFERENCES `member` (`MEMBER_IDX`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- warehouseproject.tblzipcode definition

CREATE TABLE `tblzipcode` (
  `area1` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- 트리거
CREATE DEFINER=`root`@`localhost` TRIGGER `takeout_trigger` BEFORE INSERT ON `takeout_log` FOR EACH ROW begin
	update product set product.PROD_STOCK=PROD_STOCK - new.TAKEOUT_AMOUNT where product.PROD_CODE =new.PROD_CODE;
end

#takeout시 자동으로 product 재고 감소시키는 트리거, 재고량 확인은 프로그램 단에서 해결


-- 데이터 삽입
insert into member VALUES("1","2019-01-01","소민환","010-4662-7527","부산광역시 사상구");
insert into member VALUES("2","2019-01-02","홍길동","010-4000-7000","부산광역시 서구");
insert into member VALUES("3","2019-01-03","이이이","010-5555-6666","부산광역시 사하구");

insert into product VALUES("a001","상의","코트","100","블랙","100");
insert into product VALUES("a002","하의","바지","100","화이트","200");
insert into product VALUES("a003","상의","패딩","105","네이비","300");
insert into product VALUES("a004","하의","조깅팬츠","110","화이트","300");

insert into stored_log VALUES("1","a001","1","2019-01-03","100");
insert into stored_log VALUES("2","a002","1","2019-01-04","200");
insert into stored_log VALUES("3","a002","1","2019-01-06","300");
insert into stored_log VALUES("4","a003","1","2019-01-07","300");

insert into takeout_log VALUES("1","a001","2","2019-01-10","30","없음","서울특별시 강남구 강남대로 238");
insert into takeout_log VALUES("2","a002","2","2019-01-11","30","없음","서울특별시 강남구 강남대로 239");
insert into takeout_log VALUES("3","a002","2","2019-01-12","50","없음","서울특별시 강남구 강남대로 240");
insert into takeout_log VALUES("4","a003","2","2019-01-13","40","없음","서울특별시 강남구 강남대로 241");
insert into takeout_log VALUES("5","a003","3","2019-01-14","60","없음","서울특별시 강남구 강남대로 242");
insert into takeout_log VALUES("6","a003","3","2019-01-15","70","없음","서울특별시 강남구 강남대로 243");
insert into takeout_log VALUES("7","a004","3","2019-01-16","70","없음","서울특별시 강남구 강남대로 244");

INSERT INTO warehouseproject.tblzipcode (area1) VALUES
	 ('서울특별시 강남구 강남대로 238'),
	 ('서울특별시 강남구 강남대로 239'),
	 ('서울특별시 강남구 강남대로 240'),
	 ('서울특별시 강남구 강남대로 241'),
	 ('서울특별시 강남구 강남대로 242'),
	 ('서울특별시 강남구 강남대로 243'),
	 ('서울특별시 강남구 강남대로 244'),
	 ('서울특별시 강남구 강남대로 245'),
	 ('서울특별시 강남구 강남대로 246'),
	 ('서울특별시 강남구 강남대로 246');
INSERT INTO warehouseproject.tblzipcode (area1) VALUES
	 ('서울특별시 강남구 강남대로 249'),
	 ('서울특별시 강남구 강남대로 250'),
	 ('서울특별시 강남구 강남대로 255'),
	 ('서울특별시 강남구 강남대로 256'),
	 ('서울특별시 강남구 강남대로 262'),
	 ('서울특별시 강남구 강남대로 263'),
	 ('서울특별시 강남구 강남대로 264'),
	 ('서울특별시 강남구 강남대로 265'),
	 ('서울특별시 강남구 강남대로 266'),
	 ('서울특별시 강남구 강남대로 267');
INSERT INTO warehouseproject.tblzipcode (area1) VALUES
	 ('서울특별시 강남구 강남대로 250'),
	 ('서울특별시 강남구 강남대로 272'),
	 ('서울특별시 강남구 강남대로 272'),
	 ('서울특별시 강남구 강남대로 272'),
	 ('서울특별시 강남구 강남대로 276'),
	 ('서울특별시 강남구 강남대로 238'),
	 ('서울특별시 강남구 강남대로 239'),
	 ('서울특별시 강남구 강남대로 240'),
	 ('서울특별시 강남구 강남대로 241'),
	 ('서울특별시 강남구 강남대로 242');
INSERT INTO warehouseproject.tblzipcode (area1) VALUES
	 ('서울특별시 강남구 강남대로 243'),
	 ('서울특별시 강남구 강남대로 244'),
	 ('서울특별시 강남구 강남대로 245'),
	 ('서울특별시 강남구 강남대로 246'),
	 ('서울특별시 강남구 강남대로 246'),
	 ('서울특별시 강남구 강남대로 249'),
	 ('서울특별시 강남구 강남대로 250'),
	 ('서울특별시 강남구 강남대로 255'),
	 ('서울특별시 강남구 강남대로 256'),
	 ('서울특별시 강남구 강남대로 262');
INSERT INTO warehouseproject.tblzipcode (area1) VALUES
	 ('서울특별시 강남구 강남대로 263'),
	 ('서울특별시 강남구 강남대로 264'),
	 ('서울특별시 강남구 강남대로 265'),
	 ('서울특별시 강남구 강남대로 266'),
	 ('서울특별시 강남구 강남대로 267'),
	 ('서울특별시 강남구 강남대로 250'),
	 ('서울특별시 강남구 강남대로 272'),
	 ('서울특별시 강남구 강남대로 272'),
	 ('서울특별시 강남구 강남대로 272'),
	 ('서울특별시 강남구 강남대로 276');
