# Môi trường cần cài đặt:
 - Apache Tomcat
 - Java:
  + JRE/JDK 11
  + Maven
  + Spring Boot
 - MS SQL Server
 - NodeJS:
  + npm
  + yarn

# Cách thực thi Back-end
  - Thực thi database script trong thư mục SQL
  - Thay đổi thông tin username, password, host, port kết nối đến database trong file application.properties thư mục giftapp/src/resources
  - Chạy trực tiếp bằng terminal tại thư mục gốc của project bằng lệnh: mvn spring-boot:run
  - Hoặc build ra file war và deploy lên Apache Tomcat hoặc host: Tại thư mục gốc của project, thực thi lệnh mvn package

# Cách thực thi Front-end
  - Đổi tất cả đường dẫn (link) API (tương ứng với host Back-end) trong code Front-end
  - Tại thư mục gốc của project: chạy bằng lệnh yarn, sau đó yarn serve
  
# Truy cập website với link tương ứng với host Front-end
  VD: http://localhost:3000
