plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.games"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.5")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb:3.2.5")
	implementation("org.springframework.boot:spring-boot-starter-security:3.2.5")
	implementation("io.jsonwebtoken:jjwt-api:0.12.5")
	//implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.2.4")
	implementation("org.springframework.boot:spring-boot-starter-web:3.2.5")
	//implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.mysql:mysql-connector-j")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
