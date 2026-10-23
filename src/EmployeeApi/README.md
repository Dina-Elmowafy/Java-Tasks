# Employee REST API with Oracle

مشروع تعليمي بسيط باستخدام Spring Boot وSpring Data JPA وOracle.

## لماذا Oracle يفرق؟

- Oracle يستخدم `SEQUENCE` لتوليد الـ ID، لذلك يوجد `EMPLOYEE_SEQ`.
- نوع النص في Oracle هو `VARCHAR2` والرقم هو `NUMBER`.
- الاتصال يستخدم Oracle JDBC Driver وهو `ojdbc11`.
- في Native SQL، دمج النصوص يكون بـ `||`، لذلك البحث هو
  `LIKE :name || '%'`.
- JPQL وDerived Query لا يعتمدان مباشرة على صيغة Oracle، ولذلك هما أسهل في النقل.

## إعداد قاعدة البيانات

أنشئ User في Oracle أو عدّل البيانات الموجودة في `application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=employee_user
spring.datasource.password=employee_password
```

مع `spring.jpa.hibernate.ddl-auto=update` ينشئ Hibernate الجدول والـ Sequence.
يوجد أيضًا `schema-oracle.sql` إذا أردت إنشاءهما يدويًا؛ لا تشغّله بعد أن يكون
Hibernate قد أنشأ نفس الأسماء.

## التشغيل

```bash
mvn clean test
mvn spring-boot:run
```

Base URL: `http://localhost:8080/api/employees`

## APIs

| Method | URL | الوظيفة |
|---|---|---|
| GET | `/api/employees` | جلب كل الموظفين |
| POST | `/api/employees/by-ids` | جلب الموظفين بقائمة IDs |
| POST | `/api/employees` | إضافة موظف |
| POST | `/api/employees/batch` | إضافة قائمة موظفين |
| PUT | `/api/employees/{id}` | تعديل موظف |
| PUT | `/api/employees/batch` | تعديل قائمة موظفين |
| DELETE | `/api/employees` | حذف جميع الموظفين |
| DELETE | `/api/employees/{id}` | حذف موظف بالـ ID |
| DELETE | `/api/employees/by-ids` | حذف قائمة IDs |
| GET | `/api/employees/search/function?name=ahmed` | بحث باسم function |
| GET | `/api/employees/search/jpql?name=ahmed` | بحث JPQL |
| GET | `/api/employees/search/native?name=ahmed` | بحث Native Oracle |

استخدمنا POST في get by IDs لأن إرسال Request Body مع GET غير مضمون بين
السيرفرات والأدوات المختلفة.

## أمثلة JSON

إضافة موظف:

```json
{
  "name": "Ahmed Ali",
  "age": 25,
  "phoneNumber": "01012345678"
}
```

قائمة IDs:

```json
[1, 2, 5]
```

تعديل قائمة موظفين:

```json
[
  {"id": 1, "name": "Ahmed Ali", "age": 26, "phoneNumber": "01012345678"},
  {"id": 2, "name": "Mona Adel", "age": 29, "phoneNumber": "01112345678"}
]
```

## طرق البحث الثلاث

1. Function name:
   `findByNameStartingWithIgnoreCase`، وSpring يبني الاستعلام من اسم الدالة.
2. None-native/JPQL:
   يكتب `Employee` و`e.name`، أي أسماء Entity وJava field.
3. Native query:
   يكتب `EMPLOYEES` و`NAME`، أي أسماء Oracle الحقيقية.

كل طريقة ترجع الأسماء التي تبدأ بالنص، مثل `ahmed%`.
