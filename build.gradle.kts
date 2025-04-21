plugins {
	kotlin("jvm") version "2.1.20"
	kotlin("plugin.spring") version "2.1.20"
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
	id("nu.studer.jooq") version "8.2"
}


group = "com.countries-counter"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql:42.7.4")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	jooqGenerator("org.postgresql:postgresql:42.7.4")
	jooqGenerator("org.jooq:jooq-meta-extensions")
	jooqGenerator("org.jooq:jooq-codegen")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jooq {
	version.set("3.18.6")
	configurations {
		create("main") {
			jooqConfiguration.apply {
				logging = org.jooq.meta.jaxb.Logging.WARN
				jdbc.apply {
					driver = "org.postgresql.Driver"
					url = "jdbc:postgresql://localhost:5432/countries-tracker"
					user = "user"
					password = "password"
				}
				generator.apply {
					name = "org.jooq.codegen.DefaultGenerator"
					database.apply {
						name = "org.jooq.meta.postgres.PostgresDatabase"
						inputSchema = "public"
					}
					target.apply {
						packageName = "com.countriescounter.jooq"
						directory = "build/generated-sources/jooq"
					}
				}
			}
		}
	}
}

sourceSets.main.get().java {
	srcDir(files("build/generated-sources/jooq"))
}
