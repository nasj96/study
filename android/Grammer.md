### Kotlin 기본 문법 <update...>
<hr>


#### 변수 <변경가능>
```
var intVal: Int = 10 
var doubleVal: Double = 12.3
var floatVal: Float = 12.3f
var longVal: Long = 123_456_789_123 // _ 가능
var shortVal: Short = 123
var byteVal: Byte = 127
var charVal: Char = 'a'
var stringVal: String = "abcdefg"
var boolVal: Boolean = true
```


#### 상수 <변경불가능> :: 주로 대문자로 선언됨
```
val INTVAL: Int = 10
```


<!--
#### 변수 늦은 초기화(lateint, lazy)
>바로 사용하는 변수가 아닌경우 늦은 초기화를 사용해야 메모리 손해를 막을수 있다. 사용하는 순간 메모리 할당
-->


#### 비교연산자 :: <, <=, >, >=, !=, ==, !==, ===
```
val result:Boolean = 10 > 20  // false
```


#### 논리연산자 :: &&, ||, !
```
var a:Boolean = true
var b:Boolean = true
val result:Boolean = (a && b) // true
```


#### 조건문 :: if
```
val a: Int = 10
val b: Int = 10

// true
if(a == b) { 
    Log.d("조건문", "a와 b는 같다")
} else {
    Log.d("조건문", "a와 b는 다르다")
}
```


### when (다른언어의 switch)
```
val dateVal = 2029
when(dateVal) {
    2021 -> Log.d("WHEN", "2021 입니다")
    2022, 2023, 2024 -> {
        Log.d("WHEN", "2022, 2023, 2024 중 하나 입니다")
    }
    in 2025..2027 -> {
        Log.d("WHEN", "2025~2027 중 하나 입니다")
    }
    else -> {
        Log.d("WHEN", "알수없는 값이 들어왔다")
    }
}
```
```
WHEN: 알수없는 값이 들어왔다
```


#### 배열
```
var intArr:IntArray = intArrayOf(1,2,3,4,5,6,7,8,9,10)

var intArr:IntArray = IntArray(10)
intArr[0] = 1
intArr[1] = 2
...
intArr[9] = 10


var charArr:CharArray = CharArray(7)
charArr[0] = '월'
...
charArr[6] = '일'
```


#### 컬렉션 :: mutableListOf, mutableMapOf
> Generic Type :: 변수에서 사용할 타입을 <자료타입> 로 지정한다 
```
var mutableListArr = mutableListOf<Int>()
mutableListArr.add(1) // index 0
mutableListArr.add(2) // index 1
mutableListArr.add(3) // index 2
mutableListArr.add(4) // index 3
mutableListArr.add(5) // index 4

Log.d("Collection", "index 2번의 값은 ${mutableListArr.get(2)}")
```
```
Collection: index 2번의 값은 3
```

```
var mutableMapArr = mutableMapOf<String, String>()
mutableMapArr.put("키1", "값1")
mutableMapArr.put("키2", "값2")
mutableMapArr.put("키3", "값3")
mutableMapArr.put("키4", "값4")
mutableMapArr.put("키5", "값5")

Log.d("Collection", "index 2번의 값은 ${mutableMapArr.get("키1")}")
```
```
index 2번의 값은 값1
```


#### 반복문 :: for
```
for(idx in 1..10) {
    Log.d("FOR", "idx ${idx}")
}
 
for(idx in 1 until 10) {
    Log.d("FOR", "idx ${idx}")
}
 
for(idx in 1 until 10 step 2) {
    Log.d("FOR", "idx ${idx}")
}
 
for(idx in 10 downTo 1) {
    Log.d("FOR", "idx ${idx}")
}

var intArr:IntArray = intArrayOf(1,2,3,4,5,6,7,8,9,10)
for(idx in intArr) {
    Log.d("FOR", "value = ${idx}")
}
```


#### 반복문 :: while, do while
```
var count:Int = 0
while(count<3) {
    Log.d("WHILE", "COUNT = ${count}")
    count++
}

var count2:Int = 0
do {
    Log.d("DO.WHILE", "COUNT = ${count2}")
    count2++
} while(count2<3)

```


#### 함수
> 기본
```
fun sum(): Int {
    return 1 + 2
}

fun sum(a:Int, b:Int): Int {
    return a + b
}
```

> return 바로 나오는 경우 return 생략가능
```
fun sum1(a:Int, b:Int):Int = a+b
```

> 리턴할 값이 없는 경우 Unit(Object), Unit 은 생략가능
```
fun sum3(a:Int, b:Int):Unit {
    Log.d("function", "sum3(${a}, ${b})")
}


fun sum4(a:Int, b:Int) {
    Log.d( "function", "sum4(${a}, ${b})")
}
```


#### 클래스 :: 초기화후 사용
```
class Sample {
    var count = 0
    fun sum(a:Int, b:Int) {
        count = a + b
        Log.d("Sample", "${a} + ${b} = ${count}")
    }
}
 
var cls = Sample()
cls.sum(1,2)
```


#### 클래스 :: 초기화 없이 바로 사용(companion object)
```
class Sample2 {
    companion object {
        var count = 0
        fun sum(a: Int, b: Int) {
            count = a + b
            Log.d("Sample", "${a} + ${b} = ${count}")
        }
    }
}

Sample2.sum(3, 4)
```




#### 클래스 상속
> 상속을 위해 Parent class 에게 open 를 넣어준다  
> Child는 Parent 의 변수와 메소드를 사용할수 있다
```
open class Parent {
    var count = 0
    fun echo() {
        Log.d("Sample", "count = ${count}")
    }
}

class Child : Parent() {
    fun sum(a:Int, b:Int) {
        count = a + b
    }
    fun print() {
        echo()
    }
}

var child = Child()
child.sum(10, 20)
child.print()
```


### 클래스 :: override
> override 는 Parent 에 open 이 있는 변수와 메소드를 Child에서 재정의 할 수 있음을 말한다
```
open class Parent {
    open var count = 0

    open fun echo() {
        Log.d("Parent", "count = ${count}")
    }
}

class Child : Parent() {
    override var count = 1

    fun sum(a:Int, b:Int) {
        count = count + a + b
    }
    override fun echo() {
        Log.d("Child", "count = ${count}")
    }
}

var child = Child()
child.sum(10, 20)
child.echo()
```


### 클래스 :: overload
> overload 는 파라메터가 다른 동일한 함수명을 의미
```
class Sample {
    fun getName():String {
        return "홍길동"
    }
    fun getName(prefix:String):String {
        return prefix + " 홍길동"
    }
}

var sample = Sample()
var name1:String = sample.getName()
var name2:String = sample.getName("의적")

Log.d("overload", "name1:${name1}, name2:${name2}")
```



### Nullable
```
var name:String = "홍길동"
var age:Int? = null // nullable, 메모리할당
var activity:Activity? = null // nullable, 메모리할당

// error, null exception
var result1 = age.plus(0)

// Safe call :: 변수명 뒤에 ? 붙여줌.
var result2 = age?.plus(1)

// Elvis exression :: null 인경우 초기화값 설정
var result3 = age?.plus(1) ?: 2

Log.d("Nullable", "${result2}, ${result3}") // Nullable: null, 2
```
