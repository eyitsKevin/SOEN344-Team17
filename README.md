# SOEN344-Team17
[![Build Status](https://travis-ci.com/eyitsKevin/SOEN344-Team17.svg?token=7vgmUm6UsL1MSWiHzMsy&branch=master)](https://travis-ci.com/eyitsKevin/SOEN344-Team17)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/1bd9b8dc141847e7a5664f49f9192ad9)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=eyitsKevin/SOEN344-Team17&amp;utm_campaign=Badge_Grade)

Uber Santé a walk-in clinics hassle free project is a system that will be used by patients and clinic personals.


## Requirements

Make sure all dependencies have been installed before moving on:

* [NodeJS + npm](https://nodejs.org/en/)
* [Maven](https://maven.apache.org/download.cgi) 
* [JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html)

## Installation

The recommended directory structure for the project looks like:
```shell
S0EN344-Team17/                # → Root folder for the project
├── client/                    # → Client folder where AngularCLI reside
└── server/                    # → Server folder where Java with SpringBoot reside
```

1. Clone the project: `$ git clone https://github.com/eyitsKevin/SOEN344-Team17.git`

## Local development setup

1. `cd` to the project's root folder
2. `cd server` to the project's server folder
3. Run `mvn clean install`  to execute 2 different lifecycle phases, to which plugin execution is done through pom.xml file
4. `cd ..` followed by `cd client` to project's client folder
5. `npm install` to install node modules

## General commands

### Start server service
Inside `server/` run `mvn spring-boot:run`

Server application is now available at `http://localhost:8080`

### Start client service
Inside `client/` run `npm start`

Client application is now available at `http://localhost:4200`

## Technologies
* Java with Spring Boot
* Angular7
* Maven
* MySQL

## Team Information
- Zhen Yee (40028478) - [Zhen-yee](https://github.com/Zhen-Yee)
- Michael Tang (40028150) - [TangMichael](https://github.com/TangMichael)
- Kevin Luu (40037514) - [eyitsKevin](https://github.com/eyitsKevin)
- Tristan Vu (40028927) - [phistanx](https://github.com/phistanx)
- Wu Wen Tang (40028075) - [wuwentang](https://github.com/wuwentang)
- Tamar Merdkhanian (40030718) - [TMerdkh](https://github.com/legendoftamar)
- Mathew Jackson (27709315) - [MatJackson](https://github.com/MatJackson)
