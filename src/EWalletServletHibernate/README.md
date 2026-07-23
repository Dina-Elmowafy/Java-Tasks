# E-Wallet باستخدام Servlet وHibernate

مشروع تعليمي بسيط يحقق المطلوب باستخدام Java Servlet وJSP وHibernate وH2.

## الوظائف الموجودة

- إنشاء حساب وتشفير كلمة المرور بـ PBKDF2.
- تسجيل الدخول والخروج.
- محفظة برقم فريد ورصيد.
- إيداع وسحب مع التحقق من المبلغ والرصيد.
- تحويل بين محفظتين داخل transaction واحدة.
- عرض آخر 50 معاملة.
- استخدام `@Version` لتقليل مشكلة تعديل الرصيد في نفس الوقت.
- تطبيق `GenerationType.SEQUENCE` على رقم المعاملة.

## تشغيل المشروع

المتطلبات: JDK 17 أو أحدث، Maven، وApache Tomcat 10.1 أو أحدث.

1. افتح Terminal داخل مجلد المشروع.
2. نفّذ `mvn clean test` لتجربة الـ Sequence.
3. نفّذ `mvn clean package`.
4. انسخ `target/e-wallet.war` إلى مجلد `webapps` في Tomcat.
5. شغّل Tomcat ثم افتح `http://localhost:8080/e-wallet/`.

قاعدة البيانات تحفظ في ملف H2 داخل مجلد المستخدم باسم `ewallet_student_db`.

## تجربة التحويل

أنشئ حسابين، ثم انسخ رقم محفظة الحساب الثاني. ادخل بالحساب الأول، أودع مبلغًا،
ثم استخدم رقم المحفظة الثانية في نموذج Transfer. ستظهر عمليتا
`TRANSFER_OUT` و`TRANSFER_IN` في سجل الحسابين.

## مكان تطبيق Sequence

الملف:
`src/main/java/com/student/ewallet/model/WalletTransaction.java`

الاختبار:
`src/test/java/com/student/ewallet/SequenceStrategyTest.java`

الاختبار يضيف معاملتين ويتأكد أن الرقم الأول `1000` والثاني `1001`.

> ملاحظة تعليمية: المشروع مناسب للتسليم والتعلّم. في مشروع حقيقي نضيف CSRF
> protection، rate limiting، logging، واختبارات تكامل أكثر.
