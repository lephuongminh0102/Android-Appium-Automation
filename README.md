\# Kiểm thử tự động ứng dụng Android bằng Appium



\## 1. Giới thiệu



Dự án xây dựng hệ thống kiểm thử tự động cho ứng dụng Android \*\*Cửa hàng sữa Việt\*\*, sử dụng Appium và Java nhằm kiểm tra hoạt động của các chức năng chính trong ứng dụng.



Dự án được thực hiện trong khuôn khổ đồ án môn học, tập trung vào việc xây dựng và thực thi các kịch bản kiểm thử tự động, kiểm tra kết quả thực tế và phát hiện lỗi trong quá trình sử dụng ứng dụng.



\## 2. Công nghệ sử dụng



\- \*\*Java 17:\*\* Ngôn ngữ lập trình viết các kịch bản kiểm thử.

\- \*\*Appium 2:\*\* Tự động hóa thao tác và kiểm thử ứng dụng Android.

\- \*\*Appium Java Client:\*\* Thư viện kết nối Java với Appium.

\- \*\*TestNG:\*\* Tổ chức và thực thi các bài kiểm thử.

\- \*\*Maven:\*\* Quản lý thư viện và cấu hình dự án.

\- \*\*Android Emulator:\*\* Môi trường chạy ứng dụng Android phục vụ kiểm thử.

\- \*\*Appium Inspector:\*\* Hỗ trợ kiểm tra giao diện và xác định locator của các phần tử trên ứng dụng.



\## 3. Các chức năng kiểm thử



Dự án hiện có các lớp kiểm thử cho những chức năng sau:



| Chức năng | File kiểm thử |

|---|---|

| Đăng nhập | `LoginTest.java` |

| Đăng ký tài khoản | `RegisterTest.java` |

| Thêm sản phẩm | `AddProductTest.java` |

| Thanh toán | `CheckoutTest.java` |

| Thay đổi mật khẩu | `ChangePasswordTest.java` |

| Thay đổi thông tin | `ChangeInfo.java` |



Các kịch bản kiểm thử tập trung vào việc thực hiện thao tác trên giao diện và kiểm tra kết quả tương ứng theo từng trường hợp.



\## 4. Cấu trúc dự án



```text

Test\_CuaHangSuaViet/

├── docs/

│   └── BaoCao.docx

├── src/

│   ├── main/

│   │   └── java/

│   │       └── org/example/

│   │           └── Main.java

│   └── test/

│       └── java/

│           ├── AddProductTest.java

│           ├── ChangeInfo.java

│           ├── ChangePasswordTest.java

│           ├── CheckoutTest.java

│           ├── LoginTest.java

│           └── RegisterTest.java

├── .gitignore

├── pom.xml

└── README.md

```



\## 5. Yêu cầu môi trường



Trước khi chạy kiểm thử, cần chuẩn bị:



\- JDK 17 trở lên.

\- Apache Maven.

\- Node.js và Appium 2.

\- Appium Java Client và các thư viện được khai báo trong `pom.xml`.

\- Android Studio hoặc môi trường giả lập Android tương thích.

\- Ứng dụng Cửa hàng sữa Việt đã được cài đặt trên thiết bị Android hoặc trình giả lập.



\## 6. Cài đặt và chạy kiểm thử



\### Bước 1: Tải dự án



```bash

git clone https://github.com/lephuongminh0102/Android-Appium-Automation.git

cd Android-Appium-Automation

```



\### Bước 2: Cài đặt các thư viện



```bash

mvn clean install -DskipTests

```



\### Bước 3: Khởi động thiết bị Android



Mở Android Emulator hoặc kết nối thiết bị Android đã bật USB debugging.



Kiểm tra thiết bị bằng lệnh:



```bash

adb devices

```



\### Bước 4: Khởi động Appium Server



Mở terminal riêng và chạy:



```bash

appium

```



Theo cấu hình mặc định, Appium Server sử dụng địa chỉ `http://127.0.0.1:4723`.



\### Bước 5: Cấu hình và thực thi kiểm thử



Mở dự án bằng IntelliJ IDEA, kiểm tra cấu hình kết nối Appium, thông tin thiết bị, tên package và Activity của ứng dụng.



Sau đó, chạy từng lớp kiểm thử bằng IntelliJ IDEA hoặc thực thi bằng Maven nếu các bài kiểm thử đã được cấu hình tương thích với Maven Surefire.



Ví dụ:



```bash

mvn test

```



\*\*Lưu ý:\*\* Cấu hình thực tế của từng lớp test có thể khác nhau. Cần kiểm tra cấu hình Appium, thiết bị và cách tổ chức TestNG trước khi chạy toàn bộ bộ kiểm thử bằng Maven.



\## 7. Kết quả mong đợi



\- Các kịch bản kiểm thử được thực thi trên ứng dụng Android.

\- Kết quả thực tế được đối chiếu với kết quả mong đợi.

\- Các trường hợp không đáp ứng yêu cầu được ghi nhận để phục vụ việc phân tích và sửa lỗi.

\- Kết quả kiểm thử hỗ trợ đánh giá hoạt động của các chức năng trong ứng dụng.



\## 8. Tài liệu dự án



Báo cáo đồ án được lưu trong thư mục `docs/`.



\## 9. Repository



GitHub: https://github.com/lephuongminh0102/Android-Appium-Automation



