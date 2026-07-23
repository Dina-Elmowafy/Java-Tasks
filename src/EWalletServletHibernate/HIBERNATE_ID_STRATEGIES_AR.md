# بحث: Hibernate ID Generation Strategies

## مقدمة

كل Entity في Hibernate يحتاج إلى Primary Key. نستطيع كتابة الرقم بأنفسنا، لكن
الأسهل والأكثر أمانًا أن نجعل Hibernate وقاعدة البيانات يولّدانه. نضع
`@Id` على الحقل، ثم نستخدم `@GeneratedValue` لاختيار طريقة التوليد.

## 1. GenerationType.AUTO

**التعريف:** نترك اختيار الطريقة المناسبة لـ Hibernate حسب نوع قاعدة البيانات.

```java
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
```

**طريقة العمل:** Hibernate يفحص الـ database dialect ثم قد يختار Sequence أو
Table أو طريقة أخرى مناسبة.

**المميزات:** أقل إعدادات، والكود قابل للنقل بين قواعد بيانات مختلفة.

**العيوب:** الاختيار غير واضح للمبرمج وقد يتغير عند تغيير قاعدة البيانات أو
إصدار Hibernate، ولذلك الأداء وشكل الجداول قد لا يكونان متوقعين.

**أفضل استخدام:** مشروع صغير أو تجربة أولية عندما لا نهتم بطريقة التوليد نفسها.

## 2. GenerationType.IDENTITY

**التعريف:** تعتمد على عمود Auto Increment أو Identity داخل قاعدة البيانات.

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

**طريقة العمل:** ينفّذ Hibernate أمر `INSERT` أولًا، وبعده يأخذ الرقم الذي ولّدته
قاعدة البيانات.

**المميزات:** بسيطة وشائعة، وتعمل جيدًا مع MySQL وSQL Server وH2.

**العيوب:** Hibernate يحتاج إلى تنفيذ INSERT للحصول على ID، وهذا يقلل قدرته على
تجميع عدة Inserts في Batch واحد. كما أنها ليست مناسبة لكل قواعد البيانات.

**أفضل استخدام:** تطبيق بسيط على قاعدة تدعم Identity، وعدد عمليات الإضافة ليس ضخمًا.

## 3. GenerationType.SEQUENCE

**التعريف:** تعتمد على Sequence مستقل في قاعدة البيانات يولّد أرقامًا متتابعة.

```java
@Id
@SequenceGenerator(
    name = "transaction_seq_generator",
    sequenceName = "wallet_transaction_seq",
    allocationSize = 1,
    initialValue = 1000
)
@GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "transaction_seq_generator"
)
private Long id;
```

**طريقة العمل:** يطلب Hibernate القيمة التالية من الـ Sequence قبل تنفيذ INSERT،
وبذلك يعرف ID مبكرًا.

**المميزات:** أداء جيد، ويدعم batching، ويمكن مشاركة Sequence أو تخصيص واحد لكل
Entity. باستخدام `allocationSize` أكبر يمكن تقليل مرات الاتصال بقاعدة البيانات.

**العيوب:** بعض قواعد البيانات مثل MySQL لا تدعم Sequence التقليدي. كما يجب أن
تتوافق إعدادات Hibernate مع إعدادات الـ Sequence في قاعدة البيانات. وجود فجوات
في الأرقام أمر طبيعي عند rollback أو حجز مجموعة أرقام.

**أفضل استخدام:** PostgreSQL أو Oracle أو H2، خاصة مع عدد Inserts كبير.

## شرح خصائص @SequenceGenerator

| الخاصية | معناها | تأثيرها في التطبيق |
|---|---|---|
| `name` | اسم الـ Generator داخل كود JPA | نكتب نفس الاسم في `generator` داخل `@GeneratedValue` لربطهما |
| `sequenceName` | الاسم الحقيقي للـ Sequence في قاعدة البيانات | Hibernate ينشئ/يستخدم `wallet_transaction_seq` |
| `initialValue` | أول قيمة يبدأ بها الـ Sequence عند إنشائه | في المشروع أول Transaction ID هو 1000 |
| `allocationSize` | عدد الأرقام التي يحجزها Hibernate في كل مرة | القيمة 1 تعني طلب كل ID على حدة؛ القيمة 50 تقلل الاتصالات لكنها قد تظهر فجوات أكبر |
| `schema` | Schema الذي يوجد به الـ Sequence، وهي خاصية إضافية | تستخدم عند وجود أكثر من Schema |
| `catalog` | Catalog الذي يوجد به الـ Sequence، وهي خاصية إضافية | تُستخدم فقط إذا كانت قاعدة البيانات تدعم Catalogs |
| `options` | SQL إضافي خاص بإنشاء الـ Sequence في إصدارات JPA الحديثة | نادرًا ما نحتاجه ويقلل قابلية نقل الكود |

### تأثير القيم عمليًا

- `initialValue = 1000`: أول معاملتين في الاختبار تحصلان على 1000 و1001.
- `allocationSize = 1`: Hibernate يطلب قيمة Sequence لكل Entity؛ الشرح أسهل لكن
  توجد رحلات أكثر إلى قاعدة البيانات.
- لو جعلنا `allocationSize = 50`: يحجز Hibernate مجموعة IDs محليًا، فيتحسن الأداء
  عند الإضافات الكثيرة، لكن بعد إيقاف التطبيق قد نرى قفزة في الأرقام.
- تغيير `sequenceName` يتطلب أن يوجد Sequence بهذا الاسم، أو أن يسمح الإعداد
  `hibernate.hbm2ddl.auto` لـ Hibernate بإنشائه.

## 4. GenerationType.TABLE

**التعريف:** يستخدم جدولًا عاديًا لتخزين آخر رقم تم توليده.

```java
@Id
@TableGenerator(name = "id_gen", table = "id_generator",
                pkColumnName = "entity_name", valueColumnName = "next_id",
                pkColumnValue = "wallet", allocationSize = 1)
@GeneratedValue(strategy = GenerationType.TABLE, generator = "id_gen")
private Long id;
```

**طريقة العمل:** يقرأ Hibernate صفًا من جدول التوليد ويحدّثه داخل Transaction،
ثم يستخدم الرقم في الـ Entity.

**المميزات:** تعمل تقريبًا مع أي قاعدة بيانات، حتى إن لم تدعم Sequence أو Identity.

**العيوب:** أبطأ عادةً؛ جدول التوليد قد يصبح نقطة تزاحم لأن عمليات كثيرة تحاول
قراءة وتحديث نفس الصف، ويحتاج إلى locking صحيح.

**أفضل استخدام:** عندما نحتاج حلًا مستقلًا عن نوع قاعدة البيانات ولا تتوفر
Sequence مناسبة. لا تكون الاختيار الأول للأداء العالي.

## 5. UUID

**التعريف:** معرّف 128-bit شبه فريد عالميًا، مثل
`550e8400-e29b-41d4-a716-446655440000`.

```java
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;
```

**طريقة العمل:** يستطيع Hibernate توليد UUID داخل التطبيق دون انتظار قاعدة البيانات.

**المميزات:** مناسب للأنظمة الموزعة، ويمكن توليد ID قبل الحفظ، ومن الصعب تخمين عدد
السجلات من رابط عام.

**العيوب:** أكبر من `Long`، والفهرسة قد تكون أبطأ، وUUID العشوائي يسبب توزيعًا غير
متتابع داخل index. ليس بديلًا عن صلاحيات الوصول.

**أفضل استخدام:** Microservices، الدمج بين قواعد متعددة، أو ID يظهر في API عام.

## مقارنة سريعة

| Strategy | من يولّد الرقم؟ | الأداء | قابلية النقل | الاستخدام الأفضل |
|---|---|---|---|---|
| AUTO | Hibernate يختار | حسب الاختيار | عالية | تجارب ومشاريع صغيرة |
| IDENTITY | قاعدة البيانات عند INSERT | جيد، batching محدود | متوسطة | MySQL/SQL Server وتطبيق بسيط |
| SEQUENCE | Sequence في قاعدة البيانات | ممتاز | متوسطة | PostgreSQL/Oracle وإضافات كثيرة |
| TABLE | جدول خاص | أبطأ | عالية | قاعدة لا تدعم Sequence |
| UUID | غالبًا التطبيق | جيد | عالية | أنظمة موزعة وواجهات API |

## نتيجة التجربة العملية

يوجد اختبار JUnit باسم `SequenceStrategyTest`. ينشئ جدولًا وقاعدة H2 مؤقتة،
ثم يحفظ معاملتين. بما أن `initialValue = 1000` و`allocationSize = 1`، يتأكد
الاختبار أن ID الأول 1000 والثاني 1001. نجاح `mvn test` يثبت أن الـ Sequence
تم إنشاؤه واستخدامه بصورة صحيحة.

## الخلاصة

لا توجد Strategy واحدة هي الأفضل دائمًا. `IDENTITY` هي الأسهل، و`SEQUENCE`
غالبًا أفضل للأداء عندما تدعمها قاعدة البيانات، و`TABLE` حل عام لكنه أبطأ،
و`UUID` مفيد للأنظمة الموزعة. في هذا المشروع اخترنا `SEQUENCE` للمعاملات لأنه
يوضح المطلوب عمليًا ويسمح لـ Hibernate بمعرفة ID قبل INSERT.
