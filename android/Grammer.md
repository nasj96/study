### Kotlin 기본 문법 <update...>
<hr>

#### 변수
```
var age = 10 // 변경가능  
age = 20
```

#### 상수 :: 주로 대문자로 선언됨
```
val AGE = 10 // 변경불가능  
AGE = 20 // error
```

<!--
#### 변수 늦은 초기화(lateint, lazy)
>바로 사용하는 변수가 아닌경우 늦은 초기화를 사용해야 메모리 손해를 막을수 있다. 사용하는 순간 메모리 할당
-->

#### 조건문 :: if
```
val a = 10
val b = 10

if(a == b) {
    Log.d("조건문", "a와 b는 같다")
} else {
    Log.d("조건문", "a와 b는 다르다")
}
```


#### 반복문 :: for
```
for(idx in 1..10) {
    Log.d("반복문", "idx ${idx}")
}
```

~~~
반복문: idx 1
반복문: idx 2
반복문: idx 3
반복문: idx 4
반복문: idx 5
반복문: idx 6
반복문: idx 7
반복문: idx 8
반복문: idx 9
반복문: idx 10
~~~

#### 함수
> 기본
```
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
