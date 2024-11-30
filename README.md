# Spring Boot Unit Test Öğrenme Yolculuğum (My Learning Journey)

Bu GitHub projesi, `Spring Boot` uygulamaları için birim ve entegrasyon testlerini öğrenmek ve uygulamak amacıyla geliştirilmiştir. Projede `JUnit 5`, `Mockito`, `MockMvc`, `JsonPath`, `Hamcrest` gibi test araçları ve kütüphaneler kullanılmıştır. Testlerin veri bağımlılığını azaltmak için `H2 Embedded Database` tercih edilmiş olup, gerçek veritabanı senaryoları için ise `MySQL` kullanılmıştır. Ayrıca `Test-Driven Development (TDD)` metodolojisiyle test odaklı geliştirme prensipleri uygulanmıştır.

---

### Konular (Topics)

- JUnit 5
- Mockito 
- MockMvc
- Test Driven Development (TDD)
- JsonPath
- Hamcrest
- H2 Embedded DB
- MySQL

---

## 1. Proje Hakkında (Overview)

- **Amaç**: Spring Boot projeleri için test senaryoları yazarak yazılım kalitesini artırmak ve test odaklı geliştirme yaklaşımını benimsemek.

- **Temel Özellikler**:
   - JUnit 5 ile birim testi.
   - Mockito kullanarak bağımlılıkların taklit edilmesi.
   - MockMvc ile RESTful API testleri.
   - Hamcrest ile ifadeleri özelleştirme ve doğrulama.
   - JsonPath kullanarak JSON veri yapılarını doğrulama.
   - H2 Embedded Database ile veri bağımsız testler.
   - MySQL ile entegrasyon testleri.

---

## 2. Teknik Bilgiler (Technical Details)

- **Teknolojiler ve Araçlar**:
   - Backend: Java
   - Program: Intellij IDEA
   - Test Çerçevesi: JUnit 5, Mockito, Hamcrest
   - API Testi: MockMvc, JsonPath
   - Veritabanı: H2 Embedded Database (test için), MySQL (gerçek veri için)
   - Mimari: Spring Boot, MVC

- **Mimari**: 
   - Three-Layer Architecture: Controller, Service, Repository katmanları için test senaryoları.
   - Test Odaklı Geliştirme (TDD): Kod yazmadan önce testlerin oluşturulması.

---

## 3. Kullanım (Usage)

**Gereksinimler**:
   - Java 17
   - Maven 3
   - JUnit-Spring Boot Test Dependecies
   - Spring Boot Initializr
   - Intellij IDEA
   - MySQL

**Kurulum Adımları**:
   - Bağımlılıkları yükleyin: `mvn clean install`
   - `application.properties` dosyasındaki H2 ve MySQL ayarlarını yapılandırın.

---

## 4. Öne Çıkan Özellikler (Key Features)

**Birim Testleri**:
  - Service ve Repository katmanları için bağımsız testler.
  - Mockito ile bağımlılık taklitleri.

**REST API Testleri**:
  - MockMvc kullanarak HTTP çağrılarını simüle etme.
  - JsonPath ile API yanıtlarını doğrulama.

**Veritabanı Testleri**:
  - H2 Embedded Database ile hızlı test ortamı.
  - MySQL üzerinde gerçek veri senaryoları.

**Doğrulama**:
  - Hamcrest ile özel doğrulamalar ve ifadeler.

**Test-Driven Development (TDD)**:
  - Kod yazımından önce testlerin oluşturulması.

---

## 5. Demo ve Ekran Görüntüleri (Demo & Screenshots)

Her konu için geliştirilen örnekler ve açıklamalar mevcuttur. Örnekler, terminal üzerinden veya Intellij IDEA'dan çalıştırılabilir.

---

## 6. Katkıda Bulunanlar ve Kaynaklar (Contributors & Resources)

- **Proje Sahibi**: [Batuhan Baysal](https://www.linkedin.com/in/batuhan-baysal-502656170/)

- **Kaynaklar**:
   - Spring Boot Resmi Dokümantasyonu
   - JUnit 5 ve Mockito Resmi Kılavuzları
   - Hamcrest ve JsonPath Referansları
   - Udemy Spring Boot Unit Test Bootcamp

---

## 7. İletişim ve Destek (Contact & Support)

- **LinkedIn**: [Batuhan Baysal LinkedIn Profilim](https://www.linkedin.com/in/batuhan-baysal-502656170/)

- **GitHub**: [Batuhan Baysal GitHub Profilim](https://github.com/BatuhanBaysal)
