USE [master]
GO
/****** Object:  Database [CoffeeShopAptech]    Script Date: 9/20/2015 8:05:13 PM ******/
CREATE DATABASE [CoffeeShopAptech]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BTL', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\BTL.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'BTL_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\BTL_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [CoffeeShopAptech] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CoffeeShopAptech].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CoffeeShopAptech] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET ARITHABORT OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [CoffeeShopAptech] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CoffeeShopAptech] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CoffeeShopAptech] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CoffeeShopAptech] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CoffeeShopAptech] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [CoffeeShopAptech] SET  MULTI_USER 
GO
ALTER DATABASE [CoffeeShopAptech] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CoffeeShopAptech] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CoffeeShopAptech] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CoffeeShopAptech] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [CoffeeShopAptech]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 9/20/2015 8:05:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [varchar](30) NOT NULL,
	[Password] [varchar](50) NOT NULL,
	[RoleID] [int] NOT NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 9/20/2015 8:05:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[SellerID] [int] NOT NULL,
	[CustomerName] [nvarchar](50) NULL,
	[Total] [decimal](18, 2) NOT NULL,
	[Date] [datetime] NOT NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_Bill] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Category]    Script Date: 9/20/2015 8:05:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Diary]    Script Date: 9/20/2015 8:05:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Diary](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[AccountID] [int] NOT NULL,
	[Time] [datetime] NOT NULL,
	[Description] [nvarchar](1000) NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_Diary] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Functions]    Script Date: 9/20/2015 8:05:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Functions](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_Function] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Price]    Script Date: 9/20/2015 8:05:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Price](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Value] [numeric](18, 2) NOT NULL,
	[StartDate] [datetime] NOT NULL,
	[EndDate] [datetime] NOT NULL,
	[IsActive] [bit] NOT NULL,
	[ProductID] [int] NOT NULL,
 CONSTRAINT [PK_Price] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Product]    Script Date: 9/20/2015 8:05:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](150) NOT NULL,
	[Description] [nvarchar](2000) NULL,
	[ImportDate] [datetime] NULL,
	[Quantity] [int] NOT NULL,
	[CategoryID] [int] NOT NULL,
	[PublisherID] [int] NULL,
	[UnitID] [int] NOT NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Product_Bill]    Script Date: 9/20/2015 8:05:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product_Bill](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[BillID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Price] [numeric](18, 2) NOT NULL,
 CONSTRAINT [PK_BillDetail] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Publisher]    Script Date: 9/20/2015 8:05:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Publisher](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](150) NOT NULL,
	[Certificate] [nvarchar](2000) NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_Publisher] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role]    Script Date: 9/20/2015 8:05:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role_Function]    Script Date: 9/20/2015 8:05:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role_Function](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[RoleID] [int] NOT NULL,
	[FunctionID] [int] NOT NULL,
 CONSTRAINT [PK_Role_Function] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Unit]    Script Date: 9/20/2015 8:05:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Unit](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_Unit] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([ID], [Username], [Password], [RoleID], [IsActive]) VALUES (1, N'admin', N'1', 1, 1)
INSERT [dbo].[Account] ([ID], [Username], [Password], [RoleID], [IsActive]) VALUES (2, N'user', N'1', 2, 1)
INSERT [dbo].[Account] ([ID], [Username], [Password], [RoleID], [IsActive]) VALUES (27, N'admin3', N'11', 1, 0)
SET IDENTITY_INSERT [dbo].[Account] OFF
SET IDENTITY_INSERT [dbo].[Bill] ON 

INSERT [dbo].[Bill] ([ID], [SellerID], [CustomerName], [Total], [Date], [IsActive]) VALUES (1, 1, NULL, CAST(111.00 AS Decimal(18, 2)), CAST(0x0000901A00000000 AS DateTime), 1)
INSERT [dbo].[Bill] ([ID], [SellerID], [CustomerName], [Total], [Date], [IsActive]) VALUES (2, 1, NULL, CAST(2222.00 AS Decimal(18, 2)), CAST(0x000091A700000000 AS DateTime), 1)
INSERT [dbo].[Bill] ([ID], [SellerID], [CustomerName], [Total], [Date], [IsActive]) VALUES (3, 1, N'', CAST(474.00 AS Decimal(18, 2)), CAST(0x0000A5190146289A AS DateTime), 1)
INSERT [dbo].[Bill] ([ID], [SellerID], [CustomerName], [Total], [Date], [IsActive]) VALUES (4, 1, N'', CAST(2.00 AS Decimal(18, 2)), CAST(0x0000A51901469BB3 AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[Bill] OFF
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([ID], [Name], [IsActive]) VALUES (1, N'Nước uống', 1)
INSERT [dbo].[Category] ([ID], [Name], [IsActive]) VALUES (2, N'Đồ ăn', 1)
INSERT [dbo].[Category] ([ID], [Name], [IsActive]) VALUES (3, N'Bia', 0)
SET IDENTITY_INSERT [dbo].[Category] OFF
SET IDENTITY_INSERT [dbo].[Diary] ON 

INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (3, 1, CAST(0x0000A51800E89417 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (4, 1, CAST(0x0000A51800E8941C AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (5, 1, CAST(0x0000A51800E89420 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (6, 1, CAST(0x0000A51800E89420 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (7, 1, CAST(0x0000A51800E89420 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (8, 1, CAST(0x0000A51800E89425 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (9, 1, CAST(0x0000A51800E8A00A AS DateTime), N'Created product "Product13"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (10, 1, CAST(0x0000A51800E983FC AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (11, 1, CAST(0x0000A51800E993D4 AS DateTime), N'Created publisher "nutifood"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (12, 1, CAST(0x0000A51800EA29D7 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (13, 1, CAST(0x0000A51800EAA41D AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (14, 1, CAST(0x0000A51800EB426B AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (15, 1, CAST(0x0000A51800EBF758 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (16, 1, CAST(0x0000A51800ECF732 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (17, 1, CAST(0x0000A51800ED87BB AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1003, 1, CAST(0x0000A518013404ED AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1004, 1, CAST(0x0000A51801361E6D AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1005, 1, CAST(0x0000A51801365432 AS DateTime), N'Edited role "Quản trị hệ thống"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1006, 1, CAST(0x0000A51801365D00 AS DateTime), N'Edited role "Nhân viên bán hàng"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1007, 1, CAST(0x0000A5180136656D AS DateTime), N'Edited role "Nhân viên bán hàng"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1008, 1, CAST(0x0000A51801366E87 AS DateTime), N'Edited role "Quản trị hệ thống"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1009, 1, CAST(0x0000A5180136EA10 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1010, 1, CAST(0x0000A518013769BA AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1011, 1, CAST(0x0000A518013BD8F7 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1012, 1, CAST(0x0000A518013C3E0B AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1013, 1, CAST(0x0000A518013CAFF9 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1014, 1, CAST(0x0000A518013CC8F8 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1015, 1, CAST(0x0000A518013D0356 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1016, 1, CAST(0x0000A518013D520C AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1017, 1, CAST(0x0000A518013DC67A AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1018, 1, CAST(0x0000A518013DF7A4 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1019, 1, CAST(0x0000A518013E1EC1 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1020, 1, CAST(0x0000A518013E601F AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1021, 1, CAST(0x0000A518013E6BB2 AS DateTime), N'Edited role "Quản trị hệ thống"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1022, 1, CAST(0x0000A518013E829A AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1023, 1, CAST(0x0000A518013ED76A AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1024, 2, CAST(0x0000A518013F07CF AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1025, 1, CAST(0x0000A518013F0E8B AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1026, 1, CAST(0x0000A518013F1A92 AS DateTime), N'Edited role "Nhân viên bán hàng"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1027, 2, CAST(0x0000A518013F1FFC AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1028, 1, CAST(0x0000A518013F41F3 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1029, 2, CAST(0x0000A518013F4A5B AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1030, 1, CAST(0x0000A518013F5432 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1031, 1, CAST(0x0000A518013FA33F AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1032, 2, CAST(0x0000A518013FFDC4 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1033, 1, CAST(0x0000A518014007E3 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1034, 1, CAST(0x0000A51801401803 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1035, 2, CAST(0x0000A51801402874 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1036, 1, CAST(0x0000A5180140AE59 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1037, 2, CAST(0x0000A5180140B843 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1038, 1, CAST(0x0000A5180140D614 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1039, 2, CAST(0x0000A51801410AF1 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1040, 1, CAST(0x0000A51801412911 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1041, 1, CAST(0x0000A51801424278 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1042, 2, CAST(0x0000A518014249A1 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1043, 1, CAST(0x0000A51801433E3E AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1044, 2, CAST(0x0000A5180143537A AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1045, 1, CAST(0x0000A51801437BBA AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1046, 2, CAST(0x0000A518014387AC AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1047, 1, CAST(0x0000A5180143CA96 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1048, 2, CAST(0x0000A5180143D43B AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1049, 1, CAST(0x0000A518014496BF AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1050, 1, CAST(0x0000A51801449C43 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1051, 2, CAST(0x0000A5180144A529 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1052, 1, CAST(0x0000A51801453F91 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1053, 2, CAST(0x0000A5180145469C AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1054, 1, CAST(0x0000A51801458841 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1055, 2, CAST(0x0000A51801458F14 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1056, 1, CAST(0x0000A5180145B051 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1057, 2, CAST(0x0000A5180145B8A7 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1058, 1, CAST(0x0000A5180145C413 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1059, 2, CAST(0x0000A5180145CF02 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1060, 1, CAST(0x0000A518017655EF AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1061, 1, CAST(0x0000A51801769972 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1062, 1, CAST(0x0000A5180176EC40 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1063, 1, CAST(0x0000A5180177435E AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1064, 1, CAST(0x0000A51801777C82 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1065, 1, CAST(0x0000A51801779ECD AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1066, 1, CAST(0x0000A5180177CB9E AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1067, 1, CAST(0x0000A5180178326B AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1068, 1, CAST(0x0000A5180178A1A3 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1069, 1, CAST(0x0000A5180178A92E AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1070, 1, CAST(0x0000A51801793DD1 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1071, 1, CAST(0x0000A518017949DA AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1072, 1, CAST(0x0000A5180179F186 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1073, 1, CAST(0x0000A5180179F944 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1074, 1, CAST(0x0000A518017A09B0 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1075, 1, CAST(0x0000A5180180C6D7 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1076, 1, CAST(0x0000A51900002024 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1077, 1, CAST(0x0000A519000049A3 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1078, 1, CAST(0x0000A519000096C9 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1079, 1, CAST(0x0000A5190000ECB2 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1080, 1, CAST(0x0000A519000113BD AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1081, 1, CAST(0x0000A51900015FB0 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1082, 1, CAST(0x0000A51900019C7F AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1083, 1, CAST(0x0000A5190001B215 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1084, 1, CAST(0x0000A519000212C3 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1085, 1, CAST(0x0000A51900025B43 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1086, 1, CAST(0x0000A519000293C9 AS DateTime), N'Logged in to system', 1)
GO
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1087, 1, CAST(0x0000A5190002B68E AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1088, 1, CAST(0x0000A5190002D2E1 AS DateTime), N'Edited role "Nhân viên bán hàng"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1089, 1, CAST(0x0000A5190002DE6B AS DateTime), N'Edited role "Quản trị hệ thống"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1090, 1, CAST(0x0000A5190003CDD3 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1091, 2, CAST(0x0000A5190003D64F AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1092, 2, CAST(0x0000A51900043525 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1093, 2, CAST(0x0000A51900048250 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1094, 2, CAST(0x0000A51900056368 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1095, 2, CAST(0x0000A5190005B7CF AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1096, 2, CAST(0x0000A5190005E4D4 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1097, 2, CAST(0x0000A51900064393 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1098, 2, CAST(0x0000A51900067944 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1099, 2, CAST(0x0000A5190006FB2B AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1100, 1, CAST(0x0000A5190007FD84 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1101, 1, CAST(0x0000A51900087109 AS DateTime), N'Created role "Nhân viên bảo trì"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1102, 1, CAST(0x0000A5190008710D AS DateTime), N'Grant "Xem hóa đơn" permission to "Nhân viên bảo trì"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1103, 1, CAST(0x0000A5190008710D AS DateTime), N'Grant "Bán hàng" permission to "Nhân viên bảo trì"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1104, 1, CAST(0x0000A51900088B69 AS DateTime), N'Edited role "Nhân viên bảo trì"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1105, 1, CAST(0x0000A5190008C69B AS DateTime), N'Created role "Nhân viên nhập hàng"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1106, 1, CAST(0x0000A5190008C6A0 AS DateTime), N'Grant "Quản lý đơn vị" permission to "Nhân viên nhập hàng"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1107, 1, CAST(0x0000A5190008C6A9 AS DateTime), N'Grant "Quản lý danh mục" permission to "Nhân viên nhập hàng"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1108, 1, CAST(0x0000A5190008C6A9 AS DateTime), N'Grant "Quản lý kho hàng" permission to "Nhân viên nhập hàng"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1109, 1, CAST(0x0000A5190008C6AE AS DateTime), N'Grant "Quản lý nhà cung cấp" permission to "Nhân viên nhập hàng"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1110, 1, CAST(0x0000A51900090685 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1111, 1, CAST(0x0000A51900099041 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1112, 1, CAST(0x0000A519000A11FE AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1113, 1, CAST(0x0000A51900A382A0 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1114, 1, CAST(0x0000A51900A42B32 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1115, 1, CAST(0x0000A51900C1D0F4 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1116, 1, CAST(0x0000A51900C51E46 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1117, 1, CAST(0x0000A51900C58368 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1118, 1, CAST(0x0000A51900E5D4DD AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1119, 1, CAST(0x0000A51900E60DC6 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1120, 1, CAST(0x0000A51900EA9AFD AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1121, 1, CAST(0x0000A51900EBA91F AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1122, 1, CAST(0x0000A51900EBDBE8 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1123, 1, CAST(0x0000A51900EC47C1 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1124, 1, CAST(0x0000A51900F09459 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1125, 1, CAST(0x0000A51900F222C2 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1126, 1, CAST(0x0000A51900F3E306 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1127, 1, CAST(0x0000A51900F49C52 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1128, 1, CAST(0x0000A51900F527CB AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1129, 1, CAST(0x0000A51900F59457 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1130, 1, CAST(0x0000A51900F70333 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1131, 1, CAST(0x0000A51900F7267A AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1132, 1, CAST(0x0000A51900FB793F AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1133, 1, CAST(0x0000A51900FBB0AD AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1134, 1, CAST(0x0000A51900FC01CF AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1135, 1, CAST(0x0000A51900FC546D AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1136, 1, CAST(0x0000A51900FD69D0 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1137, 1, CAST(0x0000A5190106252B AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1138, 1, CAST(0x0000A51901069AD8 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1139, 1, CAST(0x0000A5190106DBF3 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1140, 1, CAST(0x0000A519010722EF AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1141, 1, CAST(0x0000A5190109A4C6 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1142, 1, CAST(0x0000A5190109D942 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1143, 1, CAST(0x0000A519010ABE09 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1144, 1, CAST(0x0000A519010AE014 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1145, 1, CAST(0x0000A519010B028D AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1146, 1, CAST(0x0000A519010BE593 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1147, 1, CAST(0x0000A519010C5745 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1148, 1, CAST(0x0000A51901171B7B AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1149, 1, CAST(0x0000A5190117CA1A AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1150, 1, CAST(0x0000A5190118299D AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1151, 1, CAST(0x0000A519011A626F AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1152, 1, CAST(0x0000A519011BD257 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1153, 1, CAST(0x0000A519011C694F AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1154, 1, CAST(0x0000A519011C98EA AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1155, 1, CAST(0x0000A519011CEC54 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1156, 1, CAST(0x0000A519011D605B AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1157, 1, CAST(0x0000A519011D83A6 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1158, 1, CAST(0x0000A519011DDAC9 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1159, 1, CAST(0x0000A519011E41BC AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1160, 1, CAST(0x0000A519011E8409 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1161, 1, CAST(0x0000A519011F0192 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1162, 1, CAST(0x0000A519011F5EF0 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1163, 1, CAST(0x0000A519011FB084 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1164, 1, CAST(0x0000A519011FE341 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1165, 1, CAST(0x0000A519012022F5 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1166, 1, CAST(0x0000A51901461399 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1167, 1, CAST(0x0000A51901462ADA AS DateTime), N'Created bill #3', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1168, 1, CAST(0x0000A51901462AF2 AS DateTime), N'Edited product "Product2"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1169, 1, CAST(0x0000A51901462AF6 AS DateTime), N'Edited product "Product2"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1170, 1, CAST(0x0000A51901462AF6 AS DateTime), N'Edited product "hoa32"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1171, 1, CAST(0x0000A51901468EE7 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1172, 1, CAST(0x0000A51901469BB8 AS DateTime), N'Created bill #4', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1173, 1, CAST(0x0000A51901469BC1 AS DateTime), N'Edited product "Product2"', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1174, 1, CAST(0x0000A5190147C770 AS DateTime), N'Logged in to system', 1)
INSERT [dbo].[Diary] ([ID], [AccountID], [Time], [Description], [IsActive]) VALUES (1175, 1, CAST(0x0000A519014A801E AS DateTime), N'Logged in to system', 1)
SET IDENTITY_INSERT [dbo].[Diary] OFF
SET IDENTITY_INSERT [dbo].[Functions] ON 

INSERT [dbo].[Functions] ([ID], [Name], [IsActive]) VALUES (1, N'Quản lý tài khoản', 1)
INSERT [dbo].[Functions] ([ID], [Name], [IsActive]) VALUES (2, N'Phân quyền tài khoản', 1)
INSERT [dbo].[Functions] ([ID], [Name], [IsActive]) VALUES (3, N'Quản lý kho hàng', 1)
INSERT [dbo].[Functions] ([ID], [Name], [IsActive]) VALUES (4, N'Bán hàng', 1)
INSERT [dbo].[Functions] ([ID], [Name], [IsActive]) VALUES (5, N'Xem hóa đơn', 1)
INSERT [dbo].[Functions] ([ID], [Name], [IsActive]) VALUES (6, N'Quản lý đơn vị', 1)
INSERT [dbo].[Functions] ([ID], [Name], [IsActive]) VALUES (7, N'Quản lý danh mục', 1)
INSERT [dbo].[Functions] ([ID], [Name], [IsActive]) VALUES (8, N'Quản lý nhà cung cấp', 1)
INSERT [dbo].[Functions] ([ID], [Name], [IsActive]) VALUES (9, N'Thống kê', 1)
INSERT [dbo].[Functions] ([ID], [Name], [IsActive]) VALUES (10, N'Sao lưu dữ liệu', 1)
INSERT [dbo].[Functions] ([ID], [Name], [IsActive]) VALUES (11, N'Phục hồi dữ liệu', 1)
INSERT [dbo].[Functions] ([ID], [Name], [IsActive]) VALUES (12, N'Xem nhật ký', 1)
SET IDENTITY_INSERT [dbo].[Functions] OFF
SET IDENTITY_INSERT [dbo].[Price] ON 

INSERT [dbo].[Price] ([ID], [Value], [StartDate], [EndDate], [IsActive], [ProductID]) VALUES (1, CAST(11.00 AS Numeric(18, 2)), CAST(0x0000901A00000000 AS DateTime), CAST(0x0000A51A00000000 AS DateTime), 1, 1)
INSERT [dbo].[Price] ([ID], [Value], [StartDate], [EndDate], [IsActive], [ProductID]) VALUES (2, CAST(22.00 AS Numeric(18, 2)), CAST(0x000091A700000000 AS DateTime), CAST(0x0000933100000000 AS DateTime), 1, 1)
INSERT [dbo].[Price] ([ID], [Value], [StartDate], [EndDate], [IsActive], [ProductID]) VALUES (3, CAST(1.00 AS Numeric(18, 2)), CAST(0x0000A51800000000 AS DateTime), CAST(0x0000A51D00000000 AS DateTime), 1, 2)
INSERT [dbo].[Price] ([ID], [Value], [StartDate], [EndDate], [IsActive], [ProductID]) VALUES (4, CAST(2.00 AS Numeric(18, 2)), CAST(0x000094BF00000000 AS DateTime), CAST(0x0000A51E00000000 AS DateTime), 1, 2)
INSERT [dbo].[Price] ([ID], [Value], [StartDate], [EndDate], [IsActive], [ProductID]) VALUES (5, CAST(22.00 AS Numeric(18, 2)), CAST(0x000091A700000000 AS DateTime), CAST(0x0000933100000000 AS DateTime), 1, 1)
INSERT [dbo].[Price] ([ID], [Value], [StartDate], [EndDate], [IsActive], [ProductID]) VALUES (6, CAST(33.00 AS Numeric(18, 2)), CAST(0x000091A700000000 AS DateTime), CAST(0x0000933100000000 AS DateTime), 1, 4)
INSERT [dbo].[Price] ([ID], [Value], [StartDate], [EndDate], [IsActive], [ProductID]) VALUES (7, CAST(466.00 AS Numeric(18, 2)), CAST(0x0000A51500000000 AS DateTime), CAST(0x0000A51E00000000 AS DateTime), 1, 6)
INSERT [dbo].[Price] ([ID], [Value], [StartDate], [EndDate], [IsActive], [ProductID]) VALUES (8, CAST(33.00 AS Numeric(18, 2)), CAST(0x000091A700000000 AS DateTime), CAST(0x0000933100000000 AS DateTime), 1, 1003)
INSERT [dbo].[Price] ([ID], [Value], [StartDate], [EndDate], [IsActive], [ProductID]) VALUES (1007, CAST(466.00 AS Numeric(18, 2)), CAST(0x0000A51A00000000 AS DateTime), CAST(0x0000A51E00000000 AS DateTime), 1, 6)
INSERT [dbo].[Price] ([ID], [Value], [StartDate], [EndDate], [IsActive], [ProductID]) VALUES (1008, CAST(3.00 AS Numeric(18, 2)), CAST(0x000094BF00000000 AS DateTime), CAST(0x0000A51E00000000 AS DateTime), 1, 2)
SET IDENTITY_INSERT [dbo].[Price] OFF
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([ID], [Name], [Description], [ImportDate], [Quantity], [CategoryID], [PublisherID], [UnitID], [IsActive]) VALUES (1, N'Cappuccino', N'à phê được pha chế theo cách uống cà phê của người Ý, nay đã trở thành thức uống nổi tiếng trên thế giới, được mọi người yêu thích.', CAST(0x0000901A00000000 AS DateTime), 1, 1, 1, 1, 1)
INSERT [dbo].[Product] ([ID], [Name], [Description], [ImportDate], [Quantity], [CategoryID], [PublisherID], [UnitID], [IsActive]) VALUES (2, N'Espresso', N'Cho thì uống', CAST(0x000091A700000000 AS DateTime), 17, 2, 2, 2, 1)
INSERT [dbo].[Product] ([ID], [Name], [Description], [ImportDate], [Quantity], [CategoryID], [PublisherID], [UnitID], [IsActive]) VALUES (3, N'Latte', N'Trà xanh', CAST(0x0000A51400000000 AS DateTime), 3, 1, 1, 1, 0)
INSERT [dbo].[Product] ([ID], [Name], [Description], [ImportDate], [Quantity], [CategoryID], [PublisherID], [UnitID], [IsActive]) VALUES (4, N'Matcha', N'Như nước đường', CAST(0x0000A51400000000 AS DateTime), 113, 1, 1, 1, 1)
INSERT [dbo].[Product] ([ID], [Name], [Description], [ImportDate], [Quantity], [CategoryID], [PublisherID], [UnitID], [IsActive]) VALUES (6, N'Sida', N'Bệnh', CAST(0x0000A51400000000 AS DateTime), 0, 1, 2, 1, 1)
INSERT [dbo].[Product] ([ID], [Name], [Description], [ImportDate], [Quantity], [CategoryID], [PublisherID], [UnitID], [IsActive]) VALUES (1003, N'Xịp siêu nhân', N'đẹp', CAST(0x0000901A00000000 AS DateTime), 1, 1, 1, 2, 1)
INSERT [dbo].[Product] ([ID], [Name], [Description], [ImportDate], [Quantity], [CategoryID], [PublisherID], [UnitID], [IsActive]) VALUES (1004, N'Nước đậu nành', N'Ngon', CAST(0x0000902C00000000 AS DateTime), 133, 2, 1, 2, 1)
INSERT [dbo].[Product] ([ID], [Name], [Description], [ImportDate], [Quantity], [CategoryID], [PublisherID], [UnitID], [IsActive]) VALUES (1005, N'Nước miếng', N'Ngon', CAST(0x0000902C00000000 AS DateTime), 133, 2, 1, 2, 1)
INSERT [dbo].[Product] ([ID], [Name], [Description], [ImportDate], [Quantity], [CategoryID], [PublisherID], [UnitID], [IsActive]) VALUES (1006, N'Nước sông Hương', N'Ngon', CAST(0x0000901A00000000 AS DateTime), 1, 1, 1, 1, 1)
SET IDENTITY_INSERT [dbo].[Product] OFF
SET IDENTITY_INSERT [dbo].[Product_Bill] ON 

INSERT [dbo].[Product_Bill] ([ID], [BillID], [ProductID], [Quantity], [Price]) VALUES (3, 1, 1, 1, CAST(1.00 AS Numeric(18, 2)))
INSERT [dbo].[Product_Bill] ([ID], [BillID], [ProductID], [Quantity], [Price]) VALUES (4, 2, 2, 1, CAST(3.00 AS Numeric(18, 2)))
INSERT [dbo].[Product_Bill] ([ID], [BillID], [ProductID], [Quantity], [Price]) VALUES (5, 3, 2, 1, CAST(2.00 AS Numeric(18, 2)))
INSERT [dbo].[Product_Bill] ([ID], [BillID], [ProductID], [Quantity], [Price]) VALUES (6, 3, 2, 2, CAST(3.00 AS Numeric(18, 2)))
INSERT [dbo].[Product_Bill] ([ID], [BillID], [ProductID], [Quantity], [Price]) VALUES (7, 3, 6, 1, CAST(466.00 AS Numeric(18, 2)))
INSERT [dbo].[Product_Bill] ([ID], [BillID], [ProductID], [Quantity], [Price]) VALUES (8, 4, 2, 1, CAST(2.00 AS Numeric(18, 2)))
SET IDENTITY_INSERT [dbo].[Product_Bill] OFF
SET IDENTITY_INSERT [dbo].[Publisher] ON 

INSERT [dbo].[Publisher] ([ID], [Name], [Certificate], [IsActive]) VALUES (1, N'Trung Nguyên', N'certificate15', 1)
INSERT [dbo].[Publisher] ([ID], [Name], [Certificate], [IsActive]) VALUES (2, N'Mông Cổ', N'certificate2', 1)
INSERT [dbo].[Publisher] ([ID], [Name], [Certificate], [IsActive]) VALUES (3, N'Nutifood', N'khong', 1)
SET IDENTITY_INSERT [dbo].[Publisher] OFF
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([ID], [Name], [IsActive]) VALUES (1, N'Quản trị hệ thống', 1)
INSERT [dbo].[Role] ([ID], [Name], [IsActive]) VALUES (2, N'Nhân viên bán hàng', 1)
INSERT [dbo].[Role] ([ID], [Name], [IsActive]) VALUES (1022, N'Nhân viên bảo trì', 1)
INSERT [dbo].[Role] ([ID], [Name], [IsActive]) VALUES (1023, N'Nhân viên nhập hàng', 1)
SET IDENTITY_INSERT [dbo].[Role] OFF
SET IDENTITY_INSERT [dbo].[Role_Function] ON 

INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1023, 2, 4)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1024, 2, 5)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1025, 2, 12)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1026, 1, 4)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1027, 1, 7)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1028, 1, 2)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1029, 1, 5)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1030, 1, 8)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1031, 1, 11)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1032, 1, 1)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1033, 1, 9)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1034, 1, 3)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1035, 1, 10)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1036, 1, 6)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1037, 1, 12)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1038, 1022, 5)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1039, 1022, 4)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1040, 1022, 12)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1041, 1022, 11)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1042, 1022, 10)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1043, 1023, 6)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1044, 1023, 7)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1045, 1023, 3)
INSERT [dbo].[Role_Function] ([ID], [RoleID], [FunctionID]) VALUES (1046, 1023, 8)
SET IDENTITY_INSERT [dbo].[Role_Function] OFF
SET IDENTITY_INSERT [dbo].[Unit] ON 

INSERT [dbo].[Unit] ([ID], [Name], [IsActive]) VALUES (1, N'Ly', 1)
INSERT [dbo].[Unit] ([ID], [Name], [IsActive]) VALUES (2, N'Lít', 1)
INSERT [dbo].[Unit] ([ID], [Name], [IsActive]) VALUES (3, N'qqtt', 0)
SET IDENTITY_INSERT [dbo].[Unit] OFF
ALTER TABLE [dbo].[Account] ADD  CONSTRAINT [DF_Account_IsActive]  DEFAULT ((1)) FOR [IsActive]
GO
ALTER TABLE [dbo].[Bill] ADD  CONSTRAINT [DF_Bill_IsActive]  DEFAULT ((1)) FOR [IsActive]
GO
ALTER TABLE [dbo].[Category] ADD  CONSTRAINT [DF_Category_IsActive]  DEFAULT ((1)) FOR [IsActive]
GO
ALTER TABLE [dbo].[Diary] ADD  CONSTRAINT [DF_Diary_IsActive]  DEFAULT ((1)) FOR [IsActive]
GO
ALTER TABLE [dbo].[Functions] ADD  CONSTRAINT [DF_Function_IsActive]  DEFAULT ((1)) FOR [IsActive]
GO
ALTER TABLE [dbo].[Price] ADD  CONSTRAINT [DF_Price_IsActive]  DEFAULT ((1)) FOR [IsActive]
GO
ALTER TABLE [dbo].[Product] ADD  CONSTRAINT [DF_Product_IsActive]  DEFAULT ((1)) FOR [IsActive]
GO
ALTER TABLE [dbo].[Product_Bill] ADD  CONSTRAINT [DF_Product_Bill_Quantity]  DEFAULT ((1)) FOR [Quantity]
GO
ALTER TABLE [dbo].[Publisher] ADD  CONSTRAINT [DF_Publisher_IsActive]  DEFAULT ((1)) FOR [IsActive]
GO
ALTER TABLE [dbo].[Role] ADD  CONSTRAINT [DF_Role_IsActive]  DEFAULT ((1)) FOR [IsActive]
GO
ALTER TABLE [dbo].[Unit] ADD  CONSTRAINT [DF_Unit_IsActive]  DEFAULT ((1)) FOR [IsActive]
GO
ALTER TABLE [dbo].[Account]  WITH CHECK ADD  CONSTRAINT [FK_Account_Role] FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([ID])
GO
ALTER TABLE [dbo].[Account] CHECK CONSTRAINT [FK_Account_Role]
GO
ALTER TABLE [dbo].[Bill]  WITH CHECK ADD  CONSTRAINT [FK_Bill_Account] FOREIGN KEY([SellerID])
REFERENCES [dbo].[Account] ([ID])
GO
ALTER TABLE [dbo].[Bill] CHECK CONSTRAINT [FK_Bill_Account]
GO
ALTER TABLE [dbo].[Diary]  WITH CHECK ADD  CONSTRAINT [FK_Diary_Account] FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([ID])
GO
ALTER TABLE [dbo].[Diary] CHECK CONSTRAINT [FK_Diary_Account]
GO
ALTER TABLE [dbo].[Price]  WITH CHECK ADD  CONSTRAINT [FK_Price_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ID])
GO
ALTER TABLE [dbo].[Price] CHECK CONSTRAINT [FK_Price_Product]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Category] ([ID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Publisher] FOREIGN KEY([PublisherID])
REFERENCES [dbo].[Publisher] ([ID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Publisher]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Unit] FOREIGN KEY([UnitID])
REFERENCES [dbo].[Unit] ([ID])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Unit]
GO
ALTER TABLE [dbo].[Product_Bill]  WITH CHECK ADD  CONSTRAINT [FK_BillDetail_Bill] FOREIGN KEY([BillID])
REFERENCES [dbo].[Bill] ([ID])
GO
ALTER TABLE [dbo].[Product_Bill] CHECK CONSTRAINT [FK_BillDetail_Bill]
GO
ALTER TABLE [dbo].[Product_Bill]  WITH CHECK ADD  CONSTRAINT [FK_BillDetail_Product] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ID])
GO
ALTER TABLE [dbo].[Product_Bill] CHECK CONSTRAINT [FK_BillDetail_Product]
GO
ALTER TABLE [dbo].[Role_Function]  WITH CHECK ADD  CONSTRAINT [FK_Role_Function_Function] FOREIGN KEY([FunctionID])
REFERENCES [dbo].[Functions] ([ID])
GO
ALTER TABLE [dbo].[Role_Function] CHECK CONSTRAINT [FK_Role_Function_Function]
GO
ALTER TABLE [dbo].[Role_Function]  WITH CHECK ADD  CONSTRAINT [FK_Role_Function_Role] FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([ID])
GO
ALTER TABLE [dbo].[Role_Function] CHECK CONSTRAINT [FK_Role_Function_Role]
GO
USE [master]
GO
ALTER DATABASE [CoffeeShopAptech] SET  READ_WRITE 
GO
