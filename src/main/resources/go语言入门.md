# 📚 Go 语言入门学习资料（全面详解版）

---

## 目录

1. [一、Go 语言简介与环境配置](#1)
2. [二、基础语法](#2)
3. [三、函数](#3)
4. [四、数组与切片](#4)
5. [五、映射(Map)](#5)
6. [六、结构体与方法](#6)
7. [七、接口](#7)
8. [八、错误处理](#8)
9. [九、并发编程](#9)
10. [十、包与模块](#10)
11. [十一、常用标准库](#11)
12. [十二、实战练习](#12)

---

## 一、Go 语言简介与环境配置 <a id="1"></a>

### 1.1 什么是 Go 语言？

Go（又称 Golang）是 Google 于 2009 年发布的开源编程语言，由 Robert Griesemer、Rob Pike 和 Ken Thompson 设计。

**核心特点：**
- **静态类型**：编译时检查类型，安全可靠
- **编译速度快**：编译型语言，运行效率接近 C
- **内置并发支持**：goroutine + channel，并发编程极简
- **垃圾回收**：自动内存管理，无需手动释放
- **语法简洁**：只有 25 个关键字，学习成本低
- **跨平台**：支持 Windows、Linux、macOS 等

### 1.2 安装 Go

**下载地址：** https://go.dev/dl/

```bash
# Linux 安装示例
wget https://go.dev/dl/go1.21.0.linux-amd64.tar.gz
sudo tar -C /usr/local -xzf go1.21.0.linux-amd64.tar.gz

# 配置环境变量
export PATH=$PATH:/usr/local/go/bin
export GOPATH=$HOME/go
```

验证安装：
```bash
go version   # 输出: go version go1.21.0 linux/amd64
```

### 1.3 第一个 Go 程序

```go
// 文件名: hello.go
package main  // 每个Go文件必须属于一个包，main包是程序入口

import "fmt"  // 导入标准库中的fmt包（格式化输入输出）

// main函数是程序的入口点，必须放在main包中
func main() {
    fmt.Println("Hello, Go!")  // 打印一行文字
}
```

运行方式：
```bash
# 方式1：直接运行
go run hello.go

# 方式2：编译生成可执行文件
go build hello.go    # 生成 hello 可执行文件
./hello
```

---

## 二、基础语法 <a id="2"></a>

### 2.1 变量声明

Go 有多种变量声明方式：

```go
package main

import "fmt"

func main() {
    // 方式1：完整声明（var 关键字 + 类型）
    var name string = "张三"
    var age int = 25

    // 方式2：类型推断（省略类型，由编译器推断）
    var city = "北京"     // 编译器推断为 string
    var score = 95.5      // 编译器推断为 float64

    // 方式3：短变量声明（最常用，只能在函数内部使用）
    hobby := "编程"       // 等价于 var hobby string = "编程"
    height := 175         // 等价于 var height int = 175

    // 方式4：批量声明
    var (
        a int     = 10
        b string  = "hello"
        c bool    = true
    )

    // 方式5：声明但不赋值（使用零值）
    var x int      // x = 0   (int的零值)
    var y string   // y = ""  (string的零值，不是nil)
    var z bool     // z = false (bool的零值)
    var p *int     // p = nil (指针的零值)

    fmt.Println(name, age, city, score, hobby, height)
    fmt.Println(a, b, c)
    fmt.Println(x, y, z, p)
}
```

> ⚠️ **重要提示**：Go 中声明的变量必须被使用，否则编译报错！这是 Go 的设计哲学，避免无用代码。

### 2.2 常量声明

```go
package main

import "fmt"

func main() {
    // 一般常量
    const pi = 3.14159
    const greeting string = "你好"

    // 批量声明常量
    const (
        MaxSize = 100
        MinSize = 10
    )

    // ⭐ iota：常量生成器（枚举神器）
    // iota 在每个 const 块中从 0 开始，每行自动递增 1
    const (
        Sunday    = iota // 0
        Monday           // 1（iota自动递增，无需重复写）
        Tuesday          // 2
        Wednesday        // 3
        Thursday         // 4
        Friday           // 5
        Saturday         // 6
    )

    // iota 的高级用法
    const (
        _  = iota             // 0（跳过，用_忽略）
        KB = 1 << (10 * iota) // 1 << 10 = 1024
        MB = 1 << (10 * iota) // 1 << 20 = 1048576
        GB = 1 << (10 * iota) // 1 << 30
        TB = 1 << (10 * iota) // 1 << 40
    )

    fmt.Println("Sunday =", Sunday, "Monday =", Monday)
    fmt.Println("KB =", KB, "MB =", MB)
}
```

### 2.3 基本数据类型

```go
package main

import (
    "fmt"
    "math"
    "unsafe"
)

func main() {
    // ========== 整数类型 ==========
    var i8  int8   = -128            // -128 ~ 127（8位，1字节）
    var u8  uint8  = 255             // 0 ~ 255
    var i16 int16  = -32768          // -32768 ~ 32767
    var u16 uint16 = 65535           // 0 ~ 65535
    var i32 int32  = -2147483648     // 约 ±21亿
    var i64 int64  = math.MaxInt64   // 约 ±9.2×10^18

    // int 和 uint：大小取决于平台（32位系统为4字节，64位为8字节）
    var num int = 100  // 最常用

    // ========== 浮点类型 ==========
    var f32 float32 = 3.14          // 32位，约7位精度
    var f64 float64 = 3.14159265358 // 64位，约15位精度（最常用）

    // ========== 复数类型 ==========
    var c64  complex64  = 1 + 2i
    var c128 complex128 = 3 + 4i    // 128位（最常用）

    // ========== 字符与字符串 ==========
    // byte 是 uint8 的别名，rune 是 int32 的别名
    var b byte = 'A'         // byte = uint8，表示ASCII字符
    var r rune = '中'        // rune = int32，表示Unicode字符（中文等）

    // 字符串是不可变的字节序列，默认UTF-8编码
    var s1 string = "Hello, 世界"
    s2 := "Go语言"

    // ========== 布尔类型 ==========
    var flag bool = true     // 只能是 true 或 false
    // ⚠️ bool 不能和整数互转：int(flag) 会报错！

    // ========== 类型别名 ==========
    // byte = uint8
    // rune  = int32
    fmt.Printf("byte size: %d, rune size: %d\n",
        unsafe.Sizeof(b), unsafe.Sizeof(r))

    fmt.Println(i8, u8, i16, u16, i32, i64, num)
    fmt.Println(f32, f64, c64, c128)
    fmt.Println(b, r, s1, s2, flag)
}
```

### 2.4 类型转换

Go 不支持隐式类型转换，**必须显式转换**：

```go
package main

import "fmt"

func main() {
    // 整数之间转换
    var a int32 = 100
    var b int64 = int64(a)   // 必须显式转换
    var c int8  = int8(a)    // 注意：值超出范围会溢出！
    fmt.Println(a, b, c)

    // 整数与浮点转换
    var x int     = 42
    var y float64 = float64(x)   // int → float64
    var z int     = int(y)       // float64 → int（截断小数部分）
    fmt.Println(x, y, z)

    // 字符串与数值转换（需要用 strconv 包）
    import "strconv"  // 实际应在文件顶部导入

    // 字符串 → 整数
    num, err := strconv.Atoi("123")
    fmt.Println(num, err)  // 123, nil

    // 整数 → 字符串
    str := strconv.Itoa(456)
    fmt.Println(str)  // "456"

    // 字符串 → 浮点
    f, err := strconv.ParseFloat("3.14", 64)
    fmt.Println(f, err)  // 3.14, nil

    // 浮点 → 字符串
    s := strconv.FormatFloat(3.14, 'f', 2, 64)
    fmt.Println(s)  // "3.14"
}
```

### 2.5 运算符

```go
package main

import "fmt"

func main() {
    // ========== 算术运算符 ==========
    a, b := 10, 3
    fmt.Println(a + b)   // 13  加
    fmt.Println(a - b)   // 7   减
    fmt.Println(a * b)   // 30  乘
    fmt.Println(a / b)   // 3   整数除法（截断）
    fmt.Println(a % b)   // 1   取模

    // ========== 关系运算符 ==========
    fmt.Println(a == b)  // false
    fmt.Println(a != b)  // true
    fmt.Println(a > b)   // true
    fmt.Println(a >= b)  // true
    fmt.Println(a < b)   // false
    fmt.Println(a <= b)  // false

    // ========== 逻辑运算符 ==========
    x, y := true, false
    fmt.Println(x && y)  // false  逻辑与（短路）
    fmt.Println(x || y)  // true   逻辑或（短路）
    fmt.Println(!x)      // false  逻辑非

    // ========== 位运算符 ==========
    m, n := 6, 3         // 6=110, 3=011
    fmt.Println(m & n)   // 2   (110 & 011 = 010)  按位与
    fmt.Println(m | n)   // 7   (110 | 011 = 111)  按位或
    fmt.Println(m ^ n)   // 5   (110 ^ 011 = 101)  按位异或
    fmt.Println(m &^ n)  // 4   (110 &^ 011 = 100) 位清空（Go特有）
    fmt.Println(m << 1)  // 12  (110 → 1100)      左移
    fmt.Println(m >> 1)  // 3   (110 → 011)       右移

    // ========== 赋值运算符 ==========
    c := 10
    c += 5   // c = 15
    c -= 3   // c = 12
    c *= 2   // c = 24
    c /= 4   // c = 6
    c %= 4   // c = 2
    c <<= 1  // c = 4
    c >>= 1  // c = 2
    fmt.Println(c)

    // ========== 取地址与取值 ==========
    p := &a     // & 取地址，p 是 *int 类型指针
    val := *p   // * 取值（解引用）
    fmt.Println(p, val)
}
```

### 2.6 控制流程

#### 2.6.1 if-else

```go
package main

import "fmt"

func main() {
    // 基本形式（条件不需要括号，但大括号必须！）
    age := 18
    if age >= 18 {
        fmt.Println("成年人")
    } else if age >= 12 {
        fmt.Println("青少年")
    } else {
        fmt.Println("儿童")
    }

    // ⭐ 特殊形式：if 前可以加初始化语句（用分号隔开）
    // 变量 score 只在 if-else 块内有效
    if score := 85; score >= 90 {
        fmt.Println("优秀")
    } else if score >= 80 {
        fmt.Println("良好")   // 会走到这里
    } else {
        fmt.Println("一般")
    }
    // fmt.Println(score)  // ❌ 编译错误！score 在 if 外不可见

    // ⭐ Go 的 if 条件不能是赋值语句，必须是布尔表达式
    // if a = 10 {}  // ❌ 编译错误
    // if a == 10 {} // ✅ 正确
}
```

#### 2.6.2 for 循环

Go 只有 `for` 一种循环关键字，没有 `while` 和 `do-while`：

```go
package main

import "fmt"

func main() {
    // ========== 形式1：经典三段式 ==========
    for i := 0; i < 10; i++ {
        fmt.Print(i, " ")  // 0 1 2 3 4 5 6 7 8 9
    }
    fmt.Println()

    // ========== 形式2：省略初始和后置语句（类似 while）==========
    j := 0
    for j < 5 {
        fmt.Print(j, " ")  // 0 1 2 3 4
        j++
    }
    fmt.Println()

    // ========== 形式3：无限循环 ==========
    k := 0
    for {
        if k >= 3 {
            break  // 跳出循环
        }
        fmt.Print(k, " ")  // 0 1 2
        k++
    }
    fmt.Println()

    // ========== 形式4：for-range（遍历集合）==========
    // 遍历字符串（rune级别，支持中文）
    for idx, ch := range "Go语言" {
        fmt.Printf("索引:%d 字符:%c Unicode:%U\n", idx, ch, ch)
    }

    // 遍历切片
    nums := []int{10, 20, 30}
    for i, v := range nums {
        fmt.Printf("索引:%d 值:%d\n", i, v)
    }

    // 遍历map
    m := map[string]int{"a": 1, "b": 2}
    for k, v := range m {
        fmt.Printf("键:%s 值:%d\n", k, v)
    }

    // ========== break 与 continue ==========
    for n := 0; n < 10; n++ {
        if n == 3 {
            continue  // 跳过本次，继续下一次
        }
        if n == 7 {
            break     // 跳出整个循环
        }
        fmt.Print(n, " ")  // 0 1 2 4 5 6
    }
    fmt.Println()

    // ⭐ break 可以配合标签跳出外层循环
    outer:
    for x := 0; x < 3; x++ {
        for y := 0; y < 3; y++ {
            if x == 1 && y == 1 {
                break outer  // 跳出 outer 标签所在的循环
            }
            fmt.Printf("(%d,%d) ", x, y)
        }
    }
    // 输出: (0,0) (0,1) (0,2) (1,0)
}
```

#### 2.6.3 switch

```go
package main

import (
    "fmt"
    "runtime"
    "time"
)

func main() {
    // ========== 基本switch ==========
    // ⭐ Go 的 switch 自动 break，不需要手动写！
    day := "周三"
    switch day {
    case "周一":
        fmt.Println("工作周开始")
    case "周二", "周三", "周四":  // 多值匹配
        fmt.Println("工作日")
    case "周五":
        fmt.Println("快放假了")
    case "周六", "周日":
        fmt.Println("周末休息")
    default:
        fmt.Println("未知")
    }

    // ========== switch 带初始化语句 ==========
    switch score := 85; {
    case score >= 90:
        fmt.Println("优秀")
    case score >= 80:
        fmt.Println("良好")
    case score >= 60:
        fmt.Println("及格")
    default:
        fmt.Println("不及格")
    }

    // ========== 无条件switch（类似 if-else链）==========
    hour := time.Now().Hour()
    switch {
    case hour < 12:
        fmt.Println("上午")
    case hour < 18:
        fmt.Println("下午")
    default:
        fmt.Println("晚上")
    }

    // ========== fallthrough：穿透到下一个case ==========
    num := 2
    switch num {
    case 1:
        fmt.Println("一")
        fallthrough  // ⚠️ 无条件穿透到 case 2
    case 2:
        fmt.Println("二")
        fallthrough  // ⚠️ 穿透到 case 3
    case 3:
        fmt.Println("三")
        // 不加 fallthrough，自动 break
    }
    // 输出: 二 三  (注意不会执行 case 1)

    // ========== 类型switch（配合接口使用）==========
    var x interface{} = "hello"
    switch v := x.(type) {
    case string:
        fmt.Printf("字符串: %s\n", v)
    case int:
        fmt.Printf("整数: %d\n", v)
    case bool:
        fmt.Printf("布尔: %t\n", v)
    default:
        fmt.Printf("其他类型: %T\n", v)
    }
}
```

---

## 三、函数 <a id="3"></a>

### 3.1 函数定义

```go
package main

import "fmt"

// ========== 基本函数 ==========
func add(a int, b int) int {    // 参数名 + 类型 → 返回类型
    return a + b
}

// 相邻同类型参数可合并类型声明
func multiply(a, b int) int {
    return a * b
}

// ========== 多返回值（Go特色）==========
func divmod(a, b int) (int, int) {
    return a / b, a % b
}

// ========== 命名返回值 ==========
func calc(a, b int) (sum int, diff int) {
    sum = a + b    // 直接赋值给命名返回值
    diff = a - b
    return         // ⭐ 裸return，自动返回命名的变量
    // 等价于 return sum, diff
}

// ========== 可变参数 ==========
// nums 接收任意数量的 int，在函数内部是 []int 切片
func sum(nums ...int) int {
    total := 0
    for _, n := range nums {
        total += n
    }
    return total
}

func main() {
    fmt.Println(add(3, 5))           // 8
    fmt.Println(multiply(4, 6))      // 24

    q, r := divmod(17, 5)           // 多返回值
    fmt.Println(q, r)               // 3 2

    s, d := calc(10, 3)
    fmt.Println(s, d)               // 7 -3

    fmt.Println(sum(1, 2, 3))       // 6
    fmt.Println(sum(1, 2, 3, 4, 5)) // 15

    // 传递切片给可变参数
    arr := []int{10, 20, 30}
    fmt.Println(sum(arr...))        // 60（展开切片传入）
}
```

### 3.2 函数作为值与闭包

```go
package main

import "fmt"

// ========== 函数是一等公民：可以赋值给变量 ==========
func main() {
    // 函数赋值给变量
    add := func(a, b int) int {   // 匿名函数
        return a + b
    }
    fmt.Println(add(3, 5))  // 8

    // ========== 闭包：捕获外部变量 ==========
    // 闭包 = 函数 + 其引用的外部变量
    counter := makeCounter()
    fmt.Println(counter())  // 1
    fmt.Println(counter())  // 2
    fmt.Println(counter())  // 3  （同一个闭包，共享变量n）

    c2 := makeCounter()     // 新的闭包，独立的n
    fmt.Println(c2())       // 1

    // ========== 工厂函数：返回不同行为的闭包 ==========
    add5 := makeAdder(5)
    add10 := makeAdder(10)
    fmt.Println(add5(3))    // 8  (5+3)
    fmt.Println(add10(3))   // 13 (10+3)
}

func makeCounter() func() int {
    n := 0                  // 被闭包捕获的变量
    return func() int {
        n++                 // 每次调用都修改同一个n
        return n
    }
}

func makeAdder(x int) func(int) int {
    return func(y int) int {
        return x + y        // 捕获外部参数 x
    }
}
```

### 3.3 defer（延迟调用）

```go
package main

import "fmt"

// defer 语句在函数返回前执行，常用于资源释放
func main() {
    // ========== defer 的执行顺序 ==========
    // 多个 defer 按 LIFO（栈）顺序执行，即后注册的先执行
    defer fmt.Println("第一个defer")   // 最后执行
    defer fmt.Println("第二个defer")   // 第二执行
    defer fmt.Println("第三个defer")   // 第一个执行

    fmt.Println("正常执行")
    // 输出顺序:
    // 正常执行
    // 第三个defer
    // 第二个defer
    // 第一个defer

    // ========== defer 实际应用：文件操作 ==========
    // （伪代码演示思路）
    // f, err := os.Open("file.txt")
    // if err != nil { return err }
    // defer f.Close()  // 无论后面发生什么，文件都会被关闭！

    // ========== defer 修改返回值 ==========
    fmt.Println(double(5))  // 输出 11（不是10！）
}

func double(x int) (result int) {
    result = x * 2
    defer func() {
        result++  // defer 在 return 前执行，修改了命名返回值
    }()
    return result  // return 10，但defer改为11
}
```

### 3.4 init 函数

```go
package main

import "fmt"

// ⭐ init 函数：每个包可以有一个或多个 init 函数
// 在 main 函数之前自动执行，用于包的初始化
// 执行顺序：全局变量初始化 → init() → main()

var globalVar = initGlobal()

func initGlobal() int {
    fmt.Println("初始化全局变量")
    return 42
}

func init() {
    fmt.Println("init 函数执行")
}

func init() {
    fmt.Println("第二个 init 函数执行")  // 可以有多个init
}

func main() {
    fmt.Println("main 函数执行, globalVar =", globalVar)
}
// 输出:
// 初始化全局变量
// init 函数执行
// 第二个 init 函数执行
// main 函数执行, globalVar = 42
```

---

## 四、数组与切片 <a id="4"></a>

### 4.1 数组

```go
package main

import "fmt"

func main() {
    // ========== 数组声明 ==========
    // 数组是固定长度的，长度是类型的一部分！
    var arr1 [5]int            // 长度为5的int数组，零值全为0
    arr2 := [3]string{"Go", "Rust", "C"}  // 简短声明
    arr3 := [...]int{1, 2, 3, 4}  // [...] 让编译器推断长度（长度=4）

    fmt.Println(arr1)  // [0 0 0 0 0]
    fmt.Println(arr2)  // [Go Rust C]
    fmt.Println(arr3)  // [1 2 3 4]

    // 指定索引初始化
    arr4 := [5]int{1: 10, 3: 30}  // 索引1=10，索引3=30，其余为0
    fmt.Println(arr4)  // [0 10 0 30 0]

    // ⚠️ [3]int 和 [5]int 是不同类型！不能互相赋值

    // ========== 数组操作 ==========
    arr5 := [5]int{10, 20, 30, 40, 50}
    fmt.Println(arr5[0])      // 10  访问元素
    arr5[1] = 25              // 修改元素
    fmt.Println(len(arr5))    // 5   获取长度

    // 遍历
    for i, v := range arr5 {
        fmt.Printf("arr5[%d] = %d\n", i, v)
    }

    // ⚠️ 数组是值类型！传参时会复制整个数组
    modifyArray(arr5)          // 传副本，原数组不变
    fmt.Println(arr5)          // 仍然是 [10 25 30 40 50]
}

func modifyArray(a [5]int) {
    a[0] = 999  // 修改的是副本
    fmt.Println("函数内:", a) // [999 25 30 40 50]
}
```

### 4.2 切片⭐ 重点

切片是 Go 中最常用的数据结构，是数组的动态视图：

```go
package main

import "fmt"

func main() {
    // ========== 切片声明 ==========
    var s1 []int              // nil切片，len=0, cap=0
    s2 := []int{1, 2, 3}      // 有初始值的切片，len=3, cap=3
    s3 := make([]int, 5)      // make创建，len=5, cap=5，元素全为0
    s4 := make([]int, 3, 10)  // len=3, cap=10（预分配容量）

    fmt.Println(s1, len(s1), cap(s1))  // [] 0 0
    fmt.Println(s2, len(s2), cap(s2))  // [1 2 3] 3 3
    fmt.Println(s3, len(s3), cap(s3))  // [0 0 0 0 0] 5 5
    fmt.Println(s4, len(s4), cap(s4))  // [0 0 0] 3 10

    // ========== 切片操作：切割（创建子切片）==========
    arr := [10]int{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
    s5 := arr[2:7]   // 包含索引2~6（不含7），长度=5
    fmt.Println(s5)  // [2 3 4 5 6]

    // ⭐ 切片与原数组共享底层数据！修改切片会影响数组
    s5[0] = 99
    fmt.Println(arr)  // [0 1 99 3 4 5 6 7 8 9]  ← arr[2]被修改了！

    // ========== append：追加元素 ==========
    s6 := []int{1, 2, 3}
    s6 = append(s6, 4)          // 追加一个元素
    s6 = append(s6, 5, 6, 7)    // 追加多个元素
    other := []int{8, 9}
    s6 = append(s6, other...)    // 追加另一个切片（展开）
    fmt.Println(s6)  // [1 2 3 4 5 6 7 8 9]

    // ⭐ append 可能返回新的切片（容量不够时会重新分配底层数组）
    // 所以必须用 s = append(s, ...) 接收返回值！

    // ========== copy：复制切片 ==========
    src := []int{1, 2, 3, 4, 5}
    dst := make([]int, 3)
    n := copy(dst, src)  // 复制，返回实际复制的元素数
    fmt.Println(dst, n)  // [1 2 3] 3

    // ========== 切片的底层原理 ==========
    // 切片内部结构（SliceHeader）：
    //   Data: 指向底层数组的指针
    //   Len:  切片当前长度
    //   Cap:  切片容量（底层数组从Data开始的总长度）

    // 示例：理解 len 和 cap
    s7 := make([]int, 0, 5)  // len=0, cap=5
    fmt.Println(s7, len(s7), cap(s7))  // [] 0 5

    s7 = append(s7, 1, 2)    // len=2, cap=5（没超出容量，不需要重新分配）
    fmt.Println(s7, len(s7), cap(s7))  // [1 2] 2 5

    s7 = s7[0:4]             // 通过切割扩展len（不超过cap就行）
    fmt.Println(s7, len(s7), cap(s7))  // [1 2 0 0] 4 5

    // ========== 删除切片元素 ==========
    s8 := []int{1, 2, 3, 4, 5}

    // 删除索引2的元素（删除3）
    s8 = append(s8[:2], s8[3:]...)  // [1 2 4 5]
    fmt.Println(s8)

    // ========== 切片是引用类型！==========
    // 传参时不会复制底层数组
    modifySlice(s6)
    fmt.Println(s6)  // 第一个元素被修改为100
}

func modifySlice(s []int) {
    s[0] = 100  // 修改切片，原切片也受影响
}
```

### 4.3 切片扩容机制

```go
package main

import "fmt"

func main() {
    // 观察扩容过程
    s := make([]int, 0)
    oldCap := cap(s)

    for i := 0; i < 200; i++ {
        s = append(s, i)
        newCap := cap(s)
        if newCap != oldCap {
            fmt.Printf("len=%4d cap=%4d → newCap=%4d (增长 %d倍)\n",
                len(s)-1, oldCap, newCap, newCap/oldCap)
            oldCap = newCap
        }
    }
    // 大致规则（Go 1.18+）：
    // - 旧容量 < 256：新容量 = 旧容量 × 2（翻倍）
    // - 旧容量 >= 256：新容量 = 旧容量 + 旧容量×0.25 + 192
    //   即约以 1.25 倍增长，但会有额外的 192 调整
}
```

---

## 五、映射 <a id="5"></a>

```go
package main

import "fmt"

func main() {
    // ========== Map 声明与创建 ==========
    var m1 map[string]int       // nil map，不能直接写入！
    m2 := map[string]int{       // 初始化
        "apple":  5,
        "banana": 3,
    }
    m3 := make(map[string]int)  // make 创建，空但非nil，可写入

    fmt.Println(m1, m2, m3)     // map[] map[apple:5 banana:3] map[]
    m1 = make(map[string]int)   // 必须先make才能使用
    m1["test"] = 1              // ✅ 现在可以写入了

    // ========== 基本操作 ==========
    m := make(map[string]int)

    // 增/改
    m["one"]   = 1
    m["two"]   = 2
    m["three"] = 3
    m["one"]   = 11             // key存在则修改，不存在则新增

    // 查
    v := m["two"]               // 获取值
    fmt.Println(v)              // 2

    // ⭐ 检查key是否存在（重要！）
    // 访问不存在的key返回零值，但无法区分"不存在"和"值为0"
    val, ok := m["unknown"]     // ok=false表示不存在
    fmt.Println(val, ok)        // 0 false

    // 删
    delete(m, "three")          // 删除key

    // 长度
    fmt.Println(len(m))         // 2

    // ========== 遍历Map ==========
    // ⚠️ Map遍历顺序是随机的！每次运行可能不同
    scores := map[string]int{
        "Alice": 90, "Bob": 85, "Charlie": 78,
    }
    for name, score := range scores {
        fmt.Printf("%s: %d\n", name, score)
    }

    // ========== Map作为集合使用 ==========
    // Go没有内置Set，用map[T]bool模拟
    set := map[string]bool{}
    set["apple"] = true
    set["banana"] = true

    if set["apple"] {           // 检查是否在集合中
        fmt.Println("apple在集合中")
    }

    // 更高效：用 map[T]struct{}（struct{}不占内存）
    set2 := make(map[string]struct{})
    set2["cherry"] = struct{}{}
    _, exists := set2["cherry"]
    fmt.Println("cherry exists:", exists)

    // ========== Map的引用特性 ==========
    // Map是引用类型，传参时不复制
    modifyMap(scores)
    fmt.Println(scores)          // Alice 的值被修改了
}

func modifyMap(m map[string]int) {
    m["Alice"] = 100
}
```

---

## 六、结构体与方法 <a id="6"></a>

### 6.1 结构体定义与使用

```go
package main

import "fmt"

// ========== 定义结构体 ==========
type Person struct {
    Name   string
    Age    int
    Height float64
    Email  string
}

// 嵌套结构体
type Address struct {
    City    string
    Street  string
    ZipCode string
}

type Employee struct {
    Person   Person    // 嵌入另一个结构体（非匿名嵌入）
    Address  Address
    Salary   float64
}

func main() {
    // ========== 创建结构体实例 ==========
    // 方式1：按字段名赋值（推荐，顺序无关）
    p1 := Person{
        Name:   "张三",
        Age:    25,
        Height: 175.5,
        Email:  "zhangsan@example.com",
    }

    // 方式2：按顺序赋值（必须全部字段，不推荐）
    p2 := Person{"李四", 30, 180.0, "lisi@example.com"}

    // 方式3：先声明再赋值
    var p3 Person
    p3.Name = "王五"
    p3.Age = 28

    // 方式4：new（返回指针）
    p4 := new(Person)  // p4 是 *Person 类型，字段都是零值
    p4.Name = "赵六"

    // 方式5：指针初始化
    p5 := &Person{Name: "孙七", Age: 35}  // & 取地址

    fmt.Println(p1, p2, p3, p4, p5)

    // ========== 访问字段 ==========
    fmt.Println(p1.Name)   // 张三
    fmt.Println(p1.Age)    // 25
    p1.Age = 26            // 修改字段
    fmt.Println(p1.Age)    // 26

    // ⭐ Go 的指针访问字段不需要 ->，统一用 .
    fmt.Println(p4.Name)   // 赵六（自动解引用，等价于 (*p4).Name）
    fmt.Println(p5.Age)    // 35

    // ========== 嵌套结构体访问 ==========
    emp := Employee{
        Person:  Person{Name: "员工A", Age: 30},
        Address: Address{City: "北京", Street: "长安街"},
        Salary:  15000,
    }
    fmt.Println(emp.Person.Name)     // 员工A
    fmt.Println(emp.Address.City)    // 北京
}
```

### 6.2 结构体的匿名嵌入（类似继承）

```go
package main

import "fmt"

// ⭐ 匿名嵌入：实现类似"继承"的效果
type Animal struct {
    Name string
    Age  int
}

type Dog struct {
    Animal        // 匿名嵌入 Animal
    Breed string
}

type Cat struct {
    Animal        // 匿名嵌入 Animal
    Color string
}

func main() {
    d := Dog{
        Animal: Animal{Name: "旺财", Age: 3},
        Breed:  "金毛",
    }

    // ⭐ 可以直接访问嵌入结构体的字段（提升/promoted）
    fmt.Println(d.Name)     // 旺财（等价于 d.Animal.Name）
    fmt.Println(d.Age)      // 3    （等价于 d.Animal.Age）
    fmt.Println(d.Breed)    // 金毛

    // 赦名字段仍然可以通过完整路径访问
    fmt.Println(d.Animal.Name)  // 旺财
}
```

### 6.3 方法

```go
package main

import (
    "fmt"
    "math"
)

// ========== 方法 ==========
// 方法 = 函数 + 接收者，类似于其他语言的"成员函数"

type Circle struct {
    Radius float64
}

// 值接收者：方法操作的是结构体的副本
func (c Circle) Area() float64 {
    return math.Pi * c.Radius * c.Radius
}

// 值接收者：不能修改原始结构体
func (c Circle) DoubleRadius() float64 {
    c.Radius *= 2  // 修改的是副本，不影响原始值
    return c.Radius
}

// ⭐ 指针接收者：方法操作的是结构体本身，可以修改
func (c *Circle) Scale(factor float64) {
    c.Radius *= factor  // 直接修改原始值
}

// ========== 值接收者 vs 指针接收者 ==========
// 选择原则：
// 1. 需要修改接收者 → 必须用指针接收者
// 2. 结构体较大 → 用指针接收者（避免复制开销）
// 3. 需要保持一致性 → 如果某个方法用了指针，其他也用指针
// 4. 小结构体、只读 → 可以用值接收者

type Rectangle struct {
    Width, Height float64
}

func (r Rectangle) Area() float64 {
    return r.Width * r.Height
}

func (r *Rectangle) Scale(factor float64) {
    r.Width *= factor
    r.Height *= factor
}

func main() {
    c := Circle{Radius: 5}
    fmt.Println(c.Area())         // 78.539...
    fmt.Println(c.DoubleRadius()) // 10（返回副本的值）
    fmt.Println(c.Radius)         // 5（原始值没变！）

    c.Scale(2)                    // 指针接收者修改了原始值
    fmt.Println(c.Radius)         // 10（已修改）

    // ⭐ Go 会自动进行值和指针的转换
    // 值实例可以调用指针接收者的方法
    r := Rectangle{Width: 3, Height: 4}
    r.Scale(2)                    // 自动转为 (&r).Scale(2)
    fmt.Println(r.Area())         // 48 (6*8)

    // 指针实例可以调用值接收者的方法
    rPtr := &Rectangle{Width: 5, Height: 6}
    fmt.Println(rPtr.Area())      // 30，自动转为 (*rPtr).Area()

    // ========== 方法也可以定义在非结构体类型上 ==========
    // 但必须是在同一个包内定义的类型
    type MyString string

    func (s MyString) Greet() string {
        return "Hello, " + string(s)
    }

    name := MyString("Go")
    fmt.Println(name.Greet())     // Hello, Go
}
```

### 6.4 结构体标签（用于 JSON、数据库等）

```go
package main

import (
    "encoding/json"
    "fmt"
)

// 结构体标签：用反引号包裹的元数据
type User struct {
    ID       int    `json:"id"`                  // JSON序列化时字段名为 "id"
    Name     string `json:"name"`                // JSON字段名为 "name"
    Email    string `json:"email,omitempty"`     // omitempty: 为零值时省略
    Password string `json:"-"`                   // - 表示序列化时忽略此字段
    Age      int    `json:"age,omitempty"`
}

func main() {
    u := User{
        ID:       1,
        Name:     "张三",
        Email:    "zhangsan@example.com",
        Password: "secret123",  // 不会被序列化
        Age:      0,            // 零值，omitempty会省略
    }

    // 序列化为JSON
    data, err := json.Marshal(u)
    if err != nil {
        fmt.Println("错误:", err)
        return
    }
    fmt.Println(string(data))
    // 输出: {"id":1,"name":"张三","email":"zhangsan@example.com"}
    // 注意：password被忽略，age为零值被省略

    // 反序列化JSON
    jsonStr := `{"id":2,"name":"李四","email":"lisi@example.com","age":25}`
    var u2 User
    err = json.Unmarshal([]byte(jsonStr), &u2)
    if err != nil {
        fmt.Println("错误:", err)
        return
    }
    fmt.Println(u2)  // {2 李四 lisi@example.com  25}
}
```

---

## 七、接口 <a id="7"></a>

### 7.1 接口定义与实现

```go
package main

import "fmt"

// ========== 定义接口 ==========
// 接口是一组方法签名的集合
type Speaker interface {
    Speak() string
}

type Walker interface {
    Walk() string
}

// 组合接口
type SpeakerWalker interface {
    Speaker    // 嵌入Speaker接口
    Walker     // 嵌入Walker接口
}

// ========== 实现接口 ==========
// ⭐ Go 的接口是隐式实现的！不需要 declare "implements"
// 只要一个类型实现了接口的所有方法，就自动实现了该接口

type Dog struct {
    Name string
}

func (d Dog) Speak() string {
    return d.Name + "说: 汪汪!"
}

func (d Dog) Walk() string {
    return d.Name + "在散步"
}

type Cat struct {
    Name string
}

func (c Cat) Speak() string {
    return c.Name + "说: 喵喵!"
}

// Cat 只实现了 Speaker，没实现 Walker

func main() {
    // ========== 接口变量 ==========
    var s Speaker  // 接口变量可以持有任何实现了该接口的值

    s = Dog{Name: "旺财"}
    fmt.Println(s.Speak())  // 旺财说: 汪汪!

    s = Cat{Name: "喵喵"}
    fmt.Println(s.Speak())  // 喵喵说: 喵喵!

    // ⭐ 接口变量内部结构：(type, value)
    // 即接口持有一个动态类型和动态值

    // ========== 组合接口 ==========
    var sw SpeakerWalker
    sw = Dog{Name: "大黄"}
    fmt.Println(sw.Speak())  // 大黄说: 汪汪!
    fmt.Println(sw.Walk())   // 大黄在散步

    // Cat 没实现 Walker，不能赋给 SpeakerWalker
    // sw = Cat{Name: "小白"}  // ❌ 编译错误
}
```

### 7.2 空接口与类型断言

```go
package main

import "fmt"

// ========== 空接口 interface{} ==========
// 空接口没有方法要求，任何类型都实现了空接口
// Go 1.18+ 可以用 any 代替 interface{}（any 是别名）

func printAnything(val any) {  // 等价于 val interface{}
    fmt.Println(val)
}

func main() {
    // 空接口可以接受任何类型的值
    printAnything(42)
    printAnything("hello")
    printAnything(3.14)
    printAnything([]int{1, 2, 3})

    // ========== 类型断言 ==========
    // 从接口值中提取具体类型
    var i interface{} = "hello"

    // 方式1：直接断言（如果类型不匹配，会panic）
    s := i.(string)        // 断言i的底层类型是string
    fmt.Println(s)         // hello

    // 方式2：安全断言（推荐，不匹配时不会panic）
    v, ok := i.(int)       // ok=false 表示类型不匹配
    fmt.Println(v, ok)     // 0 false

    v2, ok2 := i.(string)
    fmt.Println(v2, ok2)   // hello true

    // ========== 类型switch ==========
    checkType(42)           // 整数: 42
    checkType("hello")      // 字符串: hello
    checkType(3.14)         // 浮点数: 3.14
    checkType(true)         // 布尔值: true
    checkType([]int{})      // 其他类型: []int
}

func checkType(x interface{}) {
    switch v := x.(type) {  // type 关键字在switch中用于类型判断
    case int:
        fmt.Printf("整数: %d\n", v)
    case string:
        fmt.Printf("字符串: %s\n", v)
    case float64:
        fmt.Printf("浮点数: %f\n", v)
    case bool:
        fmt.Printf("布尔值: %t\n", v)
    default:
        fmt.Printf("其他类型: %T\n", v)
    }
}
```

### 7.3 常用标准接口

```go
package main

import (
    "fmt"
    "sort"
    "strings"
)

// ========== Stringer 接口（fmt包）==========
// type Stringer interface { String() string }
// 类似 Java 的 toString()

type Point struct {
    X, Y int
}

func (p Point) String() string {    // 实现 Stringer 接口
    return fmt.Sprintf("(%d, %d)", p.X, p.Y)
}

// ========== error 接口 ==========
// type error interface { Error() string }

type MyError struct {
    Code    int
    Message string
}

func (e *MyError) Error() string {    // 实现 error 接口
    return fmt.Sprintf("错误[%d]: %s", e.Code, e.Message)
}

// ========== Sort 接口 ==========
type Student struct {
    Name  string
    Score int
}

type Students []Student

// 实现 sort.Interface 的三个方法
func (s Students) Len() int           { return len(s) }
func (s Students) Less(i, j int) bool { return s[i].Score < s[j].Score }
func (s Students) Swap(i, j int)      { s[i], s[j] = s[j], s[i] }

func main() {
    p := Point{3, 4}
    fmt.Println(p)  // (3, 4)  ← 自动调用 String() 方法

    // 自定义排序
    stu := Students{
        {"Alice", 90},
        {"Bob", 75},
        {"Charlie", 88},
    }
    sort.Sort(stu)  // 按分数升序排序
    fmt.Println(stu)

    // 字符串排序
    names := []string{"Charlie", "Alice", "Bob"}
    sort.Strings(names)  // 按字母升序
    fmt.Println(names)   // [Alice Bob Charlie]
    fmt.Println(strings.Join(names, ", "))  // Alice, Bob, Charlie
}
```

---

## 八、错误处理 <a id="8"></a>

### 8.1 error 接口

```go
package main

import (
    "errors"
    "fmt"
    "os"
    "strconv"
)

func main() {
    // ========== 基本错误处理 ==========
    // Go 没有 try-catch，用返回值 + if 判断

    result, err := divide(10, 0)
    if err != nil {                  // ⭐ 经典模式：先检查错误
        fmt.Println("错误:", err)     // 错误: 除数不能为0
        return                       // 处理错误后及时返回
    }
    fmt.Println("结果:", result)     // 没有错误时才走到这里

    // ========== errors.New 创建简单错误 ==========
    err1 := errors.New("文件不存在")
    fmt.Println(err1)

    // ========== fmt.Errorf 创建格式化错误 ==========
    err2 := fmt.Errorf("用户 %s 不存在，ID=%d", "张三", 101)
    fmt.Println(err2)

    // ========== 实际示例：文件操作 ==========
    _, err3 := os.Open("nonexistent.txt")
    if err3 != nil {
        fmt.Println("文件打开失败:", err3)
    }

    // ========== 实际示例：字符串转整数 ==========
    num, err4 := strconv.Atoi("abc")
    if err4 != nil {
        fmt.Println("转换失败:", err4)  // strconv.Atoi: parsing "abc": invalid syntax
    } else {
        fmt.Println("转换成功:", num)
    }
}

func divide(a, b float64) (float64, error) {
    if b == 0 {
        return 0, errors.New("除数不能为0")  // 返回错误
    }
    return a / b, nil                         // nil 表示无错误
}
```

### 8.2 自定义错误类型

```go
package main

import "fmt"

// ========== 自定义错误结构体 ==========
type HTTPError struct {
    StatusCode int
    URL        string
    Message    string
}

// 实现 error 接口的 Error() 方法
func (e *HTTPError) Error() string {
    return fmt.Sprintf("HTTP %d: %s (URL: %s)", e.StatusCode, e.Message, e.URL)
}

func httpRequest(url string) (string, error) {
    // 模拟请求失败
    if url == "https://error.example.com" {
        return "", &HTTPError{
            StatusCode: 404,
            URL:        url,
            Message:    "Not Found",
        }
    }
    return "OK", nil
}

func main() {
    _, err := httpRequest("https://error.example.com")
    if err != nil {
        fmt.Println(err)  // HTTP 404: Not Found (URL: https://error.example.com)

        // ⭐ 类型断言：获取自定义错误的具体信息
        if httpErr, ok := err.(*HTTPError); ok {
            fmt.Printf("状态码: %d, URL: %s\n", httpErr.StatusCode, httpErr.URL)
        }
    }
}
```

### 8.3 错误包装与解包（Go 1.13+）

```go
package main

import (
    "errors"
    "fmt"
)

// ========== 错误包装：给错误添加上下文信息 ==========
func readFile(path string) error {
    // 模拟底层错误
    err := errors.New("文件不存在")
    // 用 fmt.Errorf + %w 包装错误（保留原始错误链）
    return fmt.Errorf("读取配置文件失败: %w", err)
}

func loadConfig() error {
    err := readFile("/etc/app.conf")
    if err != nil {
        return fmt.Errorf("加载应用配置失败: %w", err)
    }
    return nil
}

func main() {
    err := loadConfig()
    if err != nil {
        fmt.Println(err)  // 加载应用配置失败: 读取配置文件失败: 文件不存在

        // ⭐ errors.Is：检查错误链中是否包含特定错误
        originalErr := errors.New("文件不存在")
        if errors.Is(err, originalErr) {
            fmt.Println("根本原因是文件不存在")
        }

        // ⭐ errors.As：检查错误链中是否包含特定类型
        // （这里演示语法，上面没有自定义类型）
        // var httpErr *HTTPError
        // if errors.As(err, &httpErr) {
        //     fmt.Println("是HTTP错误:", httpErr.StatusCode)
        // }

        // ⭐ errors.Unwrap：获取包装的底层错误
        unwrapped := errors.Unwrap(err)
        fmt.Println("上层:", unwrapped)  // 读取配置文件失败: 文件不存在
        unwrapped2 := errors.Unwrap(unwrapped)
        fmt.Println("底层:", unwrapped2)  // 文件不存在
    }
}
```

### 8.4 panic 与 recover

```go
package main

import "fmt"

// ========== panic：不可恢复的错误 ==========
// panic 会中断当前函数，执行 defer，然后向上传播
// 适用于：程序出现严重bug、不可能继续运行的情况

func mustPositive(n int) int {
    if n < 0 {
        panic(fmt.Sprintf("参数必须为正数，传入: %d", n))
    }
    return n
}

// ========== recover：捕获 panic ==========
// recover 只能在 defer 中使用，阻止 panic 继续传播
func safeDivide(a, b int) (result int, err error) {
    defer func() {
        if r := recover(); r != nil {  // 捕获panic
            err = fmt.Errorf("发生panic: %v", r)  // 将panic转为error返回
        }
    }()
    result = a / b   // 如果b=0，会触发panic: integer divide by zero
    return result, nil
}

func main() {
    // panic 示例（注释掉避免程序崩溃）
    // mustPositive(-5)  // panic: 参数必须为正数，传入: -5

    // recover 示例
    r, err := safeDivide(10, 0)
    if err != nil {
        fmt.Println("错误:", err)  // 错误: 发生panic: integer divide by zero
    } else {
        fmt.Println("结果:", r)
    }

    fmt.Println("程序继续运行...")  // 程序没有被panic中断
}
```

> 💡 **最佳实践**：优先使用 error 返回值处理错误，只在真正不可恢复的情况下使用 panic（如数组越界、空指针等）。库代码不应该 panic，应该返回 error。

---

## 九、并发编程 ⭐⭐⭐ <a id="9"></a>

### 9.1 Goroutine（轻量级线程）

```go
package main

import (
    "fmt"
    "runtime"
    "time"
)

// ========== Goroutine 基础 ==========
// goroutine 是 Go 运行时管理的轻量级线程
// 创建方式：在函数调用前加 go 关键字
// 栈初始大小仅 2KB（可动态增长），远小于 OS 线程的 1-8MB

func sayHello(name string) {
    for i := 0; i < 3; i++ {
        fmt.Printf("%s: 第%d次打招呼\n", name, i+1)
        time.Sleep(100 * time.Millisecond)  // 模拟耗时操作
    }
}

func main() {
    // 启动多个 goroutine
    go sayHello("A")  // 在新的goroutine中执行
    go sayHello("B")
    go sayHello("C")

    // ⭐ main 函数本身也是一个 goroutine
    // 如果 main 结束了，所有其他 goroutine 都会被终止！
    // 所以需要等待 goroutine 执行完毕

    // 简单等待方式（不推荐，不确定要等多久）
    time.Sleep(500 * time.Millisecond)
    fmt.Println("主函数结束")

    // ========== goroutine 数量 ==========
    fmt.Println("当前goroutine数量:", runtime.NumGoroutine())
}
```

### 9.2 Channel（通道）⭐ 核心机制

```go
package main

import "fmt"

// ========== Channel 基础 ==========
// Channel 是 goroutine 之间通信的管道
// 核心哲学："不要通过共享内存来通信，而要通过通信来共享内存"

func main() {
    // 创建无缓冲通道（同步通道）
    ch := make(chan int)

    // 创建带缓冲的通道（异步通道）
    bufCh := make(chan int, 3)  // 缓冲区大小为3

    // ========== 无缓冲通道 ==========
    // 发送和接收必须同时准备好，否则阻塞
    // 类似打电话：对方必须接听才能通话

    go func() {
        ch <- 42        // 发送数据到通道（如果没人接收，会阻塞）
        fmt.Println("发送完成")
    }()

    val := <-ch         // 从通道接收数据（如果没人发送，会阻塞）
    fmt.Println("接收:", val)  // 接收: 42

    // ========== 带缓冲通道 ==========
    // 缓冲区未满时发送不阻塞，缓冲区非空时接收不阻塞
    // 类似发短信：放进缓冲区就行，对方稍后读取

    bufCh <- 1          // 缓冲区有空间，不阻塞
    bufCh <- 2
    bufCh <- 3          // 缓冲区满了（容量3）
    // bufCh <- 4       // ❌ 缓冲区已满，会阻塞！

    fmt.Println(<-bufCh)  // 1（取出一个，缓冲区有空位了）
    fmt.Println(<-bufCh)  // 2
    fmt.Println(<-bufCh)  // 3

    // ========== 通道方向（可以在函数签名中限制）==========
    // 只发送通道：chan<- int（只能发送，不能接收）
    // 只接收通道：<-chan int（只能接收，不能发送）
    sendOnly := make(chan int)
    receiveOnly := make(chan int)

    go producer(sendOnly)
    go consumer(receiveOnly)

    // ========== 关闭通道 ==========
    close(ch)  // 关闭后不能再发送，但可以接收（接收完已有数据后返回零值）

    // 判断通道是否关闭
    v, ok := <-ch
    fmt.Println(v, ok)  // 0 false（通道已关闭且无数据）

    // ========== 遍历通道（range）==========
    ch2 := make(chan int, 5)
    go func() {
        for i := 0; i < 5; i++ {
            ch2 <- i
        }
        close(ch2)  // ⭐ 必须关闭才能用 range 遍历！
    }()

    for v := range ch2 {
        fmt.Println(v)  // 0 1 2 3 4
    }
}

func producer(ch chan<- int) {  // 只能发送
    ch <- 100
}

func consumer(ch <-chan int) {  // 只能接收
    val := <-ch
    fmt.Println("消费:", val)
}
```

### 9.3 Channel 实际应用模式

```go
package main

import (
    "fmt"
    "sync"
    "time"
)

// ========== 模式1：等待 goroutine 完成 ==========
func main() {
    // 使用 sync.WaitGroup（推荐方式）
    var wg sync.WaitGroup

    for i := 0; i < 5; i++ {
        wg.Add(1)          // 每启动一个goroutine，计数+1
        go func(id int) {
            defer wg.Done()  // goroutine完成时，计数-1
            fmt.Printf("Worker %d 开始工作\n", id)
            time.Sleep(100 * time.Millisecond)
            fmt.Printf("Worker %d 完成\n", id)
        }(i)               // ⭐ 传i给匿名函数，避免闭包问题
    }

    wg.Wait()              // 阻塞直到计数归零
    fmt.Println("所有工作完成")

    // ========== 模式2：信号通道（通知模式）==========
    done := make(chan struct{})  // struct{} 不占内存，纯信号

    go func() {
        fmt.Println("后台工作...")
        time.Sleep(200 * time.Millisecond)
        done <- struct{}{}       // 发送完成信号
    }()

    <-done  // 等待信号
    fmt.Println("后台工作完成通知收到")

    // ========== 模式3：多通道选择（select）==========
    ch1 := make(chan string)
    ch2 := make(chan string)

    go func() {
        time.Sleep(100 * time.Millisecond)
        ch1 <- "来自通道1"
    }()
    go func() {
        time.Sleep(50 * time.Millisecond)
        ch2 <- "来自通道2"
    }()

    // select 会等待多个通道操作，执行最先就绪的那个
    select {
    case msg1 := <-ch1:
        fmt.Println(msg1)
    case msg2 := <-ch2:
        fmt.Println(msg2)      // 这个先就绪（ch2先有数据）
    }

    // ========== 模式4：超时控制 ==========
    timeoutCh := make(chan int)
    go func() {
        time.Sleep(2 * time.Second)
        timeoutCh <- 1
    }()

    select {
    case <-timeoutCh:
        fmt.Println("收到数据")
    case <-time.After(1 * time.Second):  // 1秒超时
        fmt.Println("超时了！")  // 先触发
    }

    // ========== 模式5：扇出/扇入 ==========
    // 扇出：一个通道的数据分发给多个goroutine处理
    // 扇入：多个goroutine的结果汇聚到一个通道

    inputs := make(chan int, 10)
    for i := 0; i < 10; i++ {
        inputs <- i
    }
    close(inputs)

    // 扇出：3个worker处理同一个输入
    results := make(chan int, 10)
    for w := 0; w < 3; w++ {
        go func() {
            for v := range inputs {
                results <- v * 2  // 处理数据
            }
        }()
    }

    // 扇入：收集所有结果
    go func() {
        // 等待所有worker完成（这里简化处理）
        time.Sleep(200 * time.Millisecond)
        close(results)
    }()

    for r := range results {
        fmt.Println("结果:", r)
    }
}
```

### 9.4 select 详解

```go
package main

import (
    "fmt"
    "time"
)

func main() {
    // ========== select 基本规则 ==========
    // 1. select 会等待case中就绪的通道操作
    // 2. 多个case同时就绪时，随机选一个执行
    // 3. 没有case就绪时，执行default（如果有），否则阻塞

    ch1 := make(chan string)
    ch2 := make(chan string)

    go func() {
        time.Sleep(50 * time.Millisecond)
        ch1 <- "A"
        ch2 <- "B"
    }()

    // 多次select演示
    for i := 0; i < 2; i++ {
        select {
        case msg := <-ch1:
            fmt.Printf("第%d次: ch1=%s\n", i+1, msg)
        case msg := <-ch2:
            fmt.Printf("第%d次: ch2=%s\n", i+1, msg)
        }
    }

    // ========== 非阻塞通道操作（带default）==========
    ch := make(chan int, 1)
    ch <- 42

    select {
    case v := <-ch:
        fmt.Println("收到:", v)  // 大概率走这里
    default:
        fmt.Println("没有数据可用")  // 通道空时走这里
    }

    // ========== 定时任务 ==========
    tick := time.Tick(100 * time.Millisecond)  // 每隔100ms发送时间
    boom := time.After(500 * time.Millisecond) // 500ms后发送时间

    for {
        select {
        case t := <-tick:
            fmt.Println("tick:", t.Format("15:04:05"))
        case <-boom:
            fmt.Println("BOOM!")
            return
        }
    }

    // ========== 空 select：永久阻塞 ==========
    // select {}  // 没有任何case，永久阻塞（常用于让程序不退出）
}
```

### 9.5 sync 包：互斥锁与等待组

```go
package main

import (
    "fmt"
    "sync"
    "time"
)

// ========== Mutex：互斥锁 ==========
// 保护共享资源，同一时间只有一个 goroutine 可以访问

type SafeCounter struct {
    mu    sync.Mutex
    count int
}

func (c *SafeCounter) Inc() {
    c.mu.Lock()         // 加锁
    defer c.mu.Unlock() // 解锁（用defer确保一定会解锁）
    c.count++
}

func (c *SafeCounter) Value() int {
    c.mu.Lock()
    defer c.mu.Unlock()
    return c.count
}

// ========== RWMutex：读写锁 ==========
// 多个读操作可以并发，写操作独占

type SafeCache struct {
    mu   sync.RWMutex
    data map[string]string
}

func (c *SafeCache) Get(key string) string {
    c.mu.RLock()         // 读锁（多个读者可以并发）
    defer c.mu.RUnlock()
    return c.data[key]
}

func (c *SafeCache) Set(key, value string) {
    c.mu.Lock()           // 写锁（独占）
    defer c.mu.Unlock()
    c.data[key] = value
}

// ========== WaitGroup：等待一组 goroutine ==========
// 已在9.3中演示，这里补充更多细节

// ========== Once：只执行一次 ==========
var once sync.Once

func initConfig() {
    fmt.Println("初始化配置（只会执行一次）")
}

func main() {
    // 无锁的竞态问题演示（反面教材）
    counter := 0
    var wg1 sync.WaitGroup
    for i := 0; i < 1000; i++ {
        wg1.Add(1)
        go func() {
            defer wg1.Done()
            counter++  // ❌ 多个goroutine并发修改，结果不确定！
        }()
    }
    wg1.Wait()
    fmt.Println("无锁计数:", counter)  // 可能小于1000

    // 使用互斥锁（正确方式）
    sc := &SafeCounter{}
    var wg2 sync.WaitGroup
    for i := 0; i < 1000; i++ {
        wg2.Add(1)
        go func() {
            defer wg2.Done()
            sc.Inc()
        }()
    }
    wg2.Wait()
    fmt.Println("有锁计数:", sc.Value())  // 一定是1000

    // Once 示例
    for i := 0; i < 5; i++ {
        go func() {
            once.Do(initConfig)  // 无论调用多少次，只执行一次
        }()
    }
    time.Sleep(100 * time.Millisecond)

    // ========== sync.Map：并发安全的Map ==========
    var m sync.Map
    m.Store("key1", "value1")
    m.Store("key2", "value2")

    v, ok := m.Load("key1")
    fmt.Println(v, ok)    // value1 true

    m.Range(func(k, v any) bool {  // 遍历
        fmt.Println(k, v)
        return true  // 返回false停止遍历
    })
}
```

### 9.6 Context（上下文控制）⭐

```go
package main

import (
    "context"
    "fmt"
    "time"
)

// Context 用于在 goroutine 之间传递：
// - 取消信号
// - 超时时间
// - 请求范围内的值

func longOperation(ctx context.Context) error {
    select {
    case <-time.After(3 * time.Second):
        fmt.Println("操作完成")
        return nil
    case <-ctx.Done():              // ⭐ 监听取消/超时信号
        return ctx.Err()             // context.DeadlineExceeded 或 context.Canceled
    }
}

func main() {
    // ========== WithTimeout：超时控制 ==========
    ctx1, cancel1 := context.WithTimeout(context.Background(), 1*time.Second)
    defer cancel1()  // ⭐ 好习惯：即使操作完成也调用cancel释放资源

    err := longOperation(ctx1)
    if err != nil {
        fmt.Println("超时:", err)  // context deadline exceeded
    }

    // ========== WithCancel：手动取消 ==========
    ctx2, cancel2 := context.WithCancel(context.Background())

    go func() {
        time.Sleep(500 * time.Millisecond)
        cancel2()  // 手动取消
        fmt.Println("发出取消信号")
    }()

    err = longOperation(ctx2)
    if err != nil {
        fmt.Println("被取消:", err)  // context canceled
    }

    // ========== WithValue：传递请求范围内的值 ==========
    ctx3 := context.WithValue(context.Background(), "userID", 42)
    ctx3 = context.WithValue(ctx3, "requestID", "req-001")

    // 在深层函数中取回值
    processRequest(ctx3)

    // ⭐ Context 使用原则：
    // 1. 不要用 context 存传业务数据，只传请求级别的元数据
    // 2. context 应作为函数第一个参数，不要放在结构体里
    // 3. 不要传递 nil context，用 context.TODO() 代替
    // 4. context.WithValue 的 key 应使用自定义类型，避免冲突
}

func processRequest(ctx context.Context) {
    userID := ctx.Value("userID")
    reqID := ctx.Value("requestID")
    fmt.Printf("处理请求: userID=%v, requestID=%v\n", userID, reqID)
}
```

### 9.7 并发模式：生产者-消费者

```go
package main

import (
    "fmt"
    "math/rand"
    "sync"
    "time"
)

// ========== 经典生产者-消费者模式 ==========
func producer(id int, ch chan<- int, wg *sync.WaitGroup) {
    defer wg.Done()
    for i := 0; i < 5; i++ {
        num := rand.Intn(100)
        ch <- num
        fmt.Printf("生产者%d: 产生 %d\n", id, num)
        time.Sleep(50 * time.Millisecond)
    }
}

func consumer(id int, ch <-chan int, done chan<- struct{}) {
    for num := range ch {
        fmt.Printf("  消费者%d: 消费 %d\n", id, num)
        time.Sleep(100 * time.Millisecond)
    }
    done <- struct{}{}
}

func main() {
    ch := make(chan int, 10)  // 带缓冲通道
    var wg sync.WaitGroup
    done := make(chan struct{})

    // 启动3个生产者
    for i := 1; i <= 3; i++ {
        wg.Add(1)
        go producer(i, ch, &wg)
    }

    // 启动2个消费者
    for i := 1; i <= 2; i++ {
        go consumer(i, ch, done)
    }

    // 等待所有生产者完成，然后关闭通道
    go func() {
        wg.Wait()
        close(ch)  // ⭐ 关闭通道通知消费者没有更多数据了
    }()

    // 等待所有消费者完成
    for i := 0; i < 2; i++ {
        <-done
    }

    fmt.Println("所有生产和消费完成")
}
```

### 9.8 并发模式：Pipeline（流水线）

```go
package main

import "fmt"

// ========== Pipeline 模式：数据流经多个处理阶段 ==========
// 每个阶段是一个 goroutine，通过 channel 连接

// 阶段1：生成数据
func generate(nums ...int) <-chan int {
    out := make(chan int)
    go func() {
        for _, n := range nums {
            out <- n
        }
        close(out)
    }()
    return out
}

// 阶段2：平方处理
func square(in <-chan int) <-chan int {
    out := make(chan int)
    go func() {
        for n := range in {
            out <- n * n
        }
        close(out)
    }()
    return out
}

// 阶段3：打印结果
func print(in <-chan int) {
    for n := range in {
        fmt.Println(n)
    }
}

func main() {
    // 构建流水线: generate → square → print
    ch1 := generate(2, 3, 4, 5)
    ch2 := square(ch1)
    print(ch2)
    // 输出: 4 9 16 25
}
```

---

## 十、包与模块 <a id="10"></a>

### 10.1 Go Modules（项目管理）

```bash
# ========== 创建新项目 ==========
mkdir myproject
cd myproject

# 初始化模块（创建 go.mod 文件）
go mod init github.com/yourname/myproject

# go.mod 内容示例：
# module github.com/yourname/myproject
# go 1.21

# ========== 添加依赖 ==========
# go get 会自动下载依赖并更新 go.mod
go get github.com/gin-gonic/gin@v1.9.1

# go.mod 会变成：
# module github.com/yourname/myproject
# go 1.21
# require github.com/gin-gonic/gin v1.9.1

# ========== 其他常用命令 ==========
go mod tidy      # 清理无用依赖，添加缺失依赖
go mod download  # 下载所有依赖到本地缓存
go list -m all   # 列出所有依赖
go mod graph     # 显示依赖图
```

### 10.2 包的组织

```go
// ========== 项目结构示例 ==========
// myproject/
// ├── go.mod
// ├── go.sum
// ├── main.go              // main包，程序入口
// ├── utils/
// │   ├── math.go          // utils包
// │   └── string.go        // utils包
// ├── models/
// │   ├── user.go          // models包
// │   └── product.go       // models包
// └── handlers/
//     ├── api.go            // handlers包

// ========== 包的规则 ==========
// 1. 同一目录下的所有Go文件必须属于同一个包
// 2. 包名通常与目录名一致（但可以不同）
// 3. 大写字母开头的标识符是公开的，小写是私有的

// ---------- utils/math.go ----------
package utils

// 大写开头：可被其他包引用
func Add(a, b int) int {
    return a + b
}

// 小写开头：只能在当前包内使用
func subtract(a, b int) int {
    return a - b
}

// ---------- main.go ----------
package main

import (
    "fmt"
    "github.com/yourname/myproject/utils"  // 导入自定义包
)

func main() {
    result := utils.Add(3, 5)   // ✅ 可访问（大写开头）
    // utils.subtract(3, 5)     // ❌ 不可访问（小写开头）
    fmt.Println(result)
}
```

### 10.3 包的导入

```go
package main

import (
    // 标准库
    "fmt"
    "strings"

    // 给包起别名（当包名冲突时）
    str "strings"               // str.Println() 代替 strings.Println()

    // 点导入（不推荐，会污染命名空间）
    // . "math"                 // 可以直接写 Pi 而不是 math.Pi

    // 仅执行init函数，不使用其他功能
    _ "image/png"               // 注册PNG解码器，不需要直接调用
)

func main() {
    fmt.Println(strings.ToLower("HELLO"))  // hello
    fmt.Println(str.ToUpper("hello"))      // HELLO（使用别名）
}
```

---

## 十一、常用标准库 <a id="11"></a>

### 11.1 fmt — 格式化输入输出

```go
package main

import "fmt"

type Point struct{ X, Y int }

func main() {
    // ========== Print 系列 ==========
    fmt.Print("不换行")                // 不换行
    fmt.Println("自动换行")             // 自动换行，参数间加空格
    fmt.Printf("格式化: %d\n", 42)      // 格式化输出

    // ========== Printf 格式化动词 ==========
    // 通用：
    fmt.Printf("%v\n", 42)             // 默认格式 → 42
    fmt.Printf("%v\n", Point{1,2})      // 结构体 → {1 2}
    fmt.Printf("%+v\n", Point{1,2})     // 带字段名 → {X:1 Y:2}
    fmt.Printf("%#v\n", Point{1,2})     // Go语法表示 → main.Point{X:1, Y:2}
    fmt.Printf("%T\n", 42)             // 类型 → int
    fmt.Printf("%%\n")                  // 百分号本身 → %

    // 整数：
    fmt.Printf("%b\n", 10)             // 二进制 → 1010
    fmt.Printf("%d\n", 10)             // 十进制 → 10
    fmt.Printf("%o\n", 10)             // 八进制 → 12
    fmt.Printf("%x\n", 255)            // 十六进制小写 → ff
    fmt.Printf("%X\n", 255)            // 十六进制大写 → FF

    // 浮点：
    fmt.Printf("%f\n", 3.14)           // 默认6位小数 → 3.140000
    fmt.Printf("%.2f\n", 3.14159)      // 2位小数 → 3.14
    fmt.Printf("%e\n", 3.14)           // 科学计数法 → 3.140000e+00

    // 字符与字符串：
    fmt.Printf("%c\n", 65)             // ASCII字符 → A
    fmt.Printf("%c\n", 0x4E2D)         // Unicode → 中
    fmt.Printf("%s\n", "Go语言")        // 字符串 → Go语言
    fmt.Printf("%q\n", "Go")           // 带引号字符串 → "Go"

    // 宽度与对齐：
    fmt.Printf("|%10s|\n", "right")    // 右对齐，宽度10 → |     right|
    fmt.Printf("|%-10s|\n", "left")    // 左对齐，宽度10 → |left      |
    fmt.Printf("|%06d|\n", 42)         // 前导零，宽度6 → |000042|

    // ========== Scan 系列（输入）==========
    var name string
    var age int
    fmt.Print("请输入姓名和年龄: ")
    fmt.Scan(&name, &age)              // 读取输入（空格分隔）
    fmt.Printf("姓名:%s 年龄:%d\n", name, age)

    // ========== Sprintf（格式化到字符串）==========
    s := fmt.Sprintf("用户:%s, ID:%d", "张三", 1)
    fmt.Println(s)  // 用户:张三, ID:1

    // ========== Fprintf（格式化到文件等Writer）==========
    // fmt.Fprintf(os.Stdout, "Hello\n")  // 写到标准输出
}
```

### 11.2 strings — 字符串操作

```go
package main

import (
    "fmt"
    "strings"
)

func main() {
    s := "Hello, Go Language!"

    // ========== 判断 ==========
    fmt.Println(strings.Contains(s, "Go"))        // true  是否包含
    fmt.Println(strings.ContainsAny(s, "aeiou"))   // true  是否包含任一字符
    fmt.Println(strings.HasPrefix(s, "Hello"))     // true  是否以...开头
    fmt.Println(strings.HasSuffix(s, "!"))         // true  是否以...结尾
    fmt.Println(strings.EqualFold("Go", "go"))     // true  忽略大小写比较

    // ========== 查找 ==========
    fmt.Println(strings.Index(s, "Go"))            // 7    第一次出现的位置
    fmt.Println(strings.LastIndex(s, "o"))         // 8    最后一次出现的位置
    fmt.Println(strings.IndexAny(s, "aeiou"))      // 1    任一字符首次位置

    // ========== 计数 ==========
    fmt.Println(strings.Count(s, "o"))             // 2    出现次数

    // ========== 变换 ==========
    fmt.Println(strings.ToLower(s))                // hello, go language!
    fmt.Println(strings.ToUpper(s))                // HELLO, GO LANGUAGE!
    fmt.Println(strings.Title("hello world"))      // Hello World（每个单词首字母大写）
    fmt.Println(strings.TrimSpace("  hello  "))    // "hello"  去除两端空白
    fmt.Println(strings.Trim("xxhelloxx", "x"))    // "hello"  去除两端指定字符
    fmt.Println(strings.TrimLeft("xxhello", "x"))  // "hello"
    fmt.Println(strings.TrimRight("helloxx", "x")) // "hello"

    // ========== 替换 ==========
    fmt.Println(strings.Replace(s, "Go", "Rust", 1))  // Hello, Rust Language!（替换1次）
    fmt.Println(strings.ReplaceAll(s, "o", "O"))      // HellO, GO Language!

    // ========== 分割与拼接 ==========
    parts := strings.Split("a,b,c,d", ",")
    fmt.Println(parts)                              // [a b c d]

    joined := strings.Join(parts, "-")
    fmt.Println(joined)                             // a-b-c-d

    fmt.Println(strings.SplitN("a,b,c,d", ",", 2))  // [a b,c,d]（最多分割N-1次）

    // ========== 重复 ==========
    fmt.Println(strings.Repeat("Go", 3))            // GoGoGo

    // ========== 字符串Builder（高效拼接）==========
    var builder strings.Builder
    builder.WriteString("Hello")
    builder.WriteString(", ")
    builder.WriteString("World")
    result := builder.String()
    fmt.Println(result)  // Hello, World
    builder.Reset()      // 清空

    // ========== 字符串比较 ==========
    fmt.Println(strings.Compare("a", "b"))  // -1（a < b）
    fmt.Println(strings.Compare("b", "a"))  // 1 （b > a）
    fmt.Println(strings.Compare("a", "a"))  // 0 （相等）
}
```

### 11.3 strconv — 类型转换

```go
package main

import (
    "fmt"
    "strconv"
)

func main() {
    // ========== 字符串 ↔ 整数 ==========
    // Atoi: ASCII to Integer
    num, err := strconv.Atoi("123")
    fmt.Println(num, err)        // 123 nil

    // Itoa: Integer to ASCII
    s := strconv.Itoa(456)
    fmt.Println(s)               // "456"

    // ========== 字符串 ↔ 浮点数 ==========
    f, err := strconv.ParseFloat("3.14", 64)   // 64位精度
    fmt.Println(f, err)          // 3.14 nil

    s2 := strconv.FormatFloat(3.14, 'f', 2, 64)  // 格式'f'，2位小数，64位
    fmt.Println(s2)              // "3.14"

    // ========== 字符串 ↔ 布尔 ==========
    b, err := strconv.ParseBool("true")
    fmt.Println(b, err)          // true nil
    s3 := strconv.FormatBool(true)
    fmt.Println(s3)              // "true"

    // ========== 整数 ↔ 不同进制字符串 ==========
    // ParseInt: 解析指定进制的字符串
    n, err := strconv.ParseInt("ff", 16, 64)  // 16进制 → 255
    fmt.Println(n, err)          // 255 nil

    n2, _ := strconv.ParseInt("1010", 2, 64)  // 2进制 → 10
    fmt.Println(n2)              // 10

    // FormatInt: 整数 → 指定进制字符串
    fmt.Println(strconv.FormatInt(255, 16))   // "ff"
    fmt.Println(strconv.FormatInt(10, 2))     // "1010"
}
```

### 11.4 time — 时间操作

```go
package main

import (
    "fmt"
    "time"
)

func main() {
    // ========== 获取当前时间 ==========
    now := time.Now()
    fmt.Println(now)                          // 2024-01-15 10:30:45.123456789 +0800 CST
    fmt.Println(now.Year())                   // 2024
    fmt.Println(now.Month())                  // January
    fmt.Println(now.Day())                    // 15
    fmt.Println(now.Hour())                   // 10
    fmt.Println(now.Minute())                 // 30
    fmt.Println(now.Second())                 // 45
    fmt.Println(now.Nanosecond())             // 123456789
    fmt.Println(now.Weekday())                // Monday

    // ========== 时间格式化 ==========
    // ⭐ Go 的时间格式化很特殊！使用参考时间：Mon Jan 2 15:04:05 MST 2006
    // 记忆口诀：01/02 03:04:05PM '06 -0700
    // 即：月/日 时:分:秒 年 时区

    fmt.Println(now.Format("2006-01-02"))             // 2024-01-15
    fmt.Println(now.Format("2006-01-02 15:04:05"))    // 2024-01-15 10:30:45
    fmt.Println(now.Format("15:04:05"))               // 10:30:45
    fmt.Println(now.Format(time.RFC3339))             // 2024-01-15T10:30:45+08:00

    // ========== 字符串解析为时间 ==========
    t, err := time.Parse("2006-01-02", "2024-06-15")
    if err != nil {
        fmt.Println("解析错误:", err)
    }
    fmt.Println(t)  // 2024-06-15 00:00:00 +0000 UTC

    // 解析当前时区的时间（推荐）
    loc, _ := time.LoadLocation("Asia/Shanghai")
    t2, _ := time.ParseInLocation("2006-01-02 15:04:05", "2024-06-15 14:30:00", loc)
    fmt.Println(t2)

    // ========== 时间计算 ==========
    // Duration（时间长度）
    fmt.Println(time.Hour)       // 1h0m0s
    fmt.Println(time.Minute)     // 1m0s
    fmt.Println(time.Second)     // 1s
    fmt.Println(time.Millisecond)// 1ms

    // 加减时间
    tomorrow := now.Add(24 * time.Hour)
    fmt.Println("明天:", tomorrow.Format("2006-01-02"))

    yesterday := now.Add(-24 * time.Hour)
    fmt.Println("昨天:", yesterday.Format("2006-01-02"))

    // 计算时间差
    diff := tomorrow.Sub(now)
    fmt.Println("时间差:", diff)          // 24h0m0s
    fmt.Println("小时:", diff.Hours())   // 24

    // ========== 休眠 ==========
    time.Sleep(100 * time.Millisecond)  // 休眠100毫秒

    // ========== 定时器 ==========
    timer := time.NewTimer(2 * time.Second)
    <-timer.C  // 等待定时器触发
    fmt.Println("定时器触发")

    // ========== 周期定时器 ==========
    ticker := time.NewTicker(500 * time.Millisecond)
    go func() {
        for t := range ticker.C {
            fmt.Println("Tick at", t.Format("15:04:05"))
        }
    }()
    time.Sleep(2 * time.Second)
    ticker.Stop()  // 停止定时器

    // ========== Unix 时间戳 ==========
    fmt.Println(now.Unix())         // 秒级时间戳: 1705288245
    fmt.Println(now.UnixMilli())    // 毫秒级时间戳
    fmt.Println(now.UnixNano())     // 纳秒级时间戳

    // 时间戳 → 时间
    t3 := time.Unix(1705288245, 0)
    fmt.Println(t3)
}
```

### 11.5 文件操作

```go
package main

import (
    "bufio"
    "fmt"
    "io"
    "os"
)

func main() {
    // ========== 写文件 ==========

    // 方式1：直接写入（适合小文件）
    data := []byte("Hello, Go文件操作！\n第二行内容\n")
    err := os.WriteFile("test.txt", data, 0644)  // 0644 = 文件权限
    if err != nil {
        fmt.Println("写入错误:", err)
        return
    }

    // 方式2：打开文件后写入（适合大文件、需要追加）
    f, err := os.OpenFile("test.txt", os.O_APPEND|os.O_WRONLY, 0644)
    if err != nil {
        fmt.Println("打开错误:", err)
        return
    }
    defer f.Close()  // ⭐ 用defer确保文件关闭

    f.WriteString("追加的内容\n")

    // 方式3：使用 bufio.Writer（带缓冲，高效）
    f2, err := os.Create("buffered.txt")
    if err != nil {
        return
    }
    defer f2.Close()

    writer := bufio.NewWriter(f2)
    writer.WriteString("缓冲写入第一行\n")
    writer.WriteString("缓冲写入第二行\n")
    writer.Flush()  // ⭐ 必须Flush将缓冲数据写入文件！

    // ========== 读文件 ==========

    // 方式1：一次性读取全部（适合小文件）
    content, err := os.ReadFile("test.txt")
    if err != nil {
        fmt.Println("读取错误:", err)
        return
    }
    fmt.Println(string(content))

    // 方式2：逐行读取（适合大文件）
    f3, err := os.Open("test.txt")
    if err != nil {
        return
    }
    defer f3.Close()

    scanner := bufio.NewScanner(f3)
    for scanner.Scan() {
        line := scanner.Text()  // 读取一行
        fmt.Println("行:", line)
    }
    if err := scanner.Err(); err != nil {
        fmt.Println("扫描错误:", err)
    }

    // 方式3：指定大小读取
    f4, err := os.Open("test.txt")
    if err != nil {
        return
    }
    defer f4.Close()

    buf := make([]byte, 1024)
    for {
        n, err := f4.Read(buf)
        if n > 0 {
            fmt.Print(string(buf[:n]))
        }
        if err == io.EOF {
            break  // 文件读完
        }
        if err != nil {
            fmt.Println("读取错误:", err)
            break
        }
    }

    // ========== 文件信息 ==========
    info, err := os.Stat("test.txt")
    if err != nil {
        return
    }
    fmt.Println("文件名:", info.Name())
    fmt.Println("大小:", info.Size())
    fmt.Println("修改时间:", info.ModTime())
    fmt.Println("是目录:", info.IsDir())
    fmt.Println("权限:", info.Mode())

    // ========== 目录操作 ==========
    os.Mkdir("mydir", 0755)                   // 创建目录
    os.MkdirAll("mydir/sub/deep", 0755)       // 创建多级目录
    os.Remove("test.txt")                     // 删除文件
    os.RemoveAll("mydir")                     // 删除目录及内容

    // 遍历目录
    entries, err := os.ReadDir(".")
    if err != nil {
        return
    }
    for _, entry := range entries {
        fmt.Println(entry.Name(), entry.IsDir())
    }
}
```

### 11.6 JSON 处理

```go
package main

import (
    "encoding/json"
    "fmt"
)

type Student struct {
    Name  string   `json:"name"`
    Age   int      `json:"age"`
    Grade string   `json:"grade"`
    Tags  []string `json:"tags,omitempty"`  // 为空时省略
}

func main() {
    // ========== 编码（Go → JSON）==========
    stu := Student{
        Name:  "张三",
        Age:   20,
        Grade: "大二",
        Tags:  []string{"优秀", "奖学金"},
    }

    // Marshal：结构体 → JSON字节
    data, err := json.Marshal(stu)
    if err != nil {
        fmt.Println("编码错误:", err)
        return
    }
    fmt.Println(string(data))
    // {"name":"张三","age":20,"grade":"大二","tags":["优秀","奖学金"]}

    // MarshalIndent：带缩进的美化输出
    dataIndent, _ := json.MarshalIndent(stu, "", "  ")
    fmt.Println(string(dataIndent))
    // {
    //   "name": "张三",
    //   "age": 20,
    //   "grade": "大二",
    //   "tags": ["优秀", "奖学金"]
    // }

    // ========== 解码（JSON → Go）==========
    jsonStr := `{"name":"李四","age":22,"grade":"大三"}`
    var stu2 Student
    err = json.Unmarshal([]byte(jsonStr), &stu2)  // ⭐ 必须传指针！
    if err != nil {
        fmt.Println("解码错误:", err)
        return
    }
    fmt.Println(stu2)  // {李四 22 大三 []}

    // ========== 处理不确定结构的JSON ==========
    jsonStr2 := `{"key":"value","number":42,"array":[1,2,3]}`
    var result map[string]interface{}
    json.Unmarshal([]byte(jsonStr2), &result)
    fmt.Println(result)

    // 需要类型断言来访问
    arr := result["array"].([]interface{})
    for i, v := range arr {
        fmt.Printf("array[%d] = %v (type: %T)\n", i, v, v)
    }
}
```

---

## 十二、实战练习 <a id="12"></a>

### 练习1：猜数字游戏

```go
package main

import (
    "fmt"
    "math/rand"
    "time"
)

func main() {
    rand.Seed(time.Now().UnixNano())  // 设置随机种子
    target := rand.Intn(100) + 1      // 1-100的随机数
    attempts := 0

    fmt.Println("欢迎来到猜数字游戏！范围1-100")

    for {
        var guess int
        fmt.Print("请输入你的猜测: ")
        _, err := fmt.Scan(&guess)
        if err != nil {
            fmt.Println("输入无效，请输入整数")
            continue
        }

        attempts++

        if guess < target {
            fmt.Println("太小了！再试试")
        } else if guess > target {
            fmt.Println("太大了！再试试")
        } else {
            fmt.Printf("恭喜！你猜对了！用了 %d 次\n", attempts)
            break
        }
    }
}
```

### 练习2：并发下载模拟

```go
package main

import (
    "fmt"
    "math/rand"
    "sync"
    "time"
)

type DownloadResult struct {
    URL     string
    Success bool
    Time    time.Duration
}

func downloadFile(url string) DownloadResult {
    duration := time.Duration(rand.Intn(500)+100) * time.Millisecond
    time.Sleep(duration)  // 模拟下载耗时

    success := rand.Float32() > 0.2  // 80% 成功率
    return DownloadResult{
        URL:     url,
        Success: success,
        Time:    duration,
    }
}

func main() {
    urls := []string{
        "https://example.com/file1",
        "https://example.com/file2",
        "https://example.com/file3",
        "https://example.com/file4",
        "https://example.com/file5",
    }

    results := make(chan DownloadResult, len(urls))
    var wg sync.WaitGroup

    start := time.Now()

    // 并发下载所有文件
    for _, url := range urls {
        wg.Add(1)
        go func(u string) {
            defer wg.Done()
            results <- downloadFile(u)
        }(url)
    }

    // 等待所有下载完成
    go func() {
        wg.Wait()
        close(results)
    }()

    // 收集结果
    successCount := 0
    for result := range results {
        status := "✓"
        if !result.Success {
            status = "✗"
        } else {
            successCount++
        }
        fmt.Printf("%s %s (%v)\n", status, result.URL, result.Time)
    }

    total := time.Since(start)
    fmt.Printf("\n成功: %d/%d, 总耗时: %v\n", successCount, len(urls), total)
}
```

### 练习3：简单HTTP服务器

```go
package main

import (
    "encoding/json"
    "fmt"
    "net/http"
    "sync"
)

// ========== 简单的REST API服务器 ==========

var (
    users = make(map[int]User)
    mu    sync.Mutex
    nextID = 1
)

type User struct {
    ID   int    `json:"id"`
    Name string `json:"name"`
    Age  int    `json:"age"`
}

func usersHandler(w http.ResponseWriter, r *http.Request) {
    mu.Lock()
    defer mu.Unlock()

    switch r.Method {
    case "GET":
        // 返回所有用户
        w.Header().Set("Content-Type", "application/json")
        list := make([]User, 0, len(users))
        for _, u := range users {
            list = append(list, u)
        }
        json.NewEncoder(w).Encode(list)

    case "POST":
        // 创建新用户
        var u User
        if err := json.NewDecoder(r.Body).Decode(&u); err != nil {
            http.Error(w, err.Error(), http.StatusBadRequest)
            return
        }
        u.ID = nextID
        nextID++
        users[u.ID] = u
        w.Header().Set("Content-Type", "application/json")
        json.NewEncoder(w).Encode(u)

    default:
        http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
    }
}

func main() {
    // 初始化一些数据
    users[1] = User{ID: 1, Name: "张三", Age: 25}
    nextID = 2

    http.HandleFunc("/users", usersHandler)

    fmt.Println("服务器启动在 http://localhost:8080")
    err := http.ListenAndServe(":8080", nil)
    if err != nil {
        fmt.Println("服务器错误:", err)
    }
}

// 测试:
// GET  http://localhost:8080/users     → 获取所有用户
// POST http://localhost:8080/users     → 创建用户
//      Body: {"name":"李四","age":30}
```

---

## 📋 知识点速查表

| 类别 | 知识点 | 关键要点 |
|------|--------|---------|
| **变量** | var/:=/零值 | `:=`只能函数内用，变量必须使用 |
| **常量** | const/iota | iota从0自增，用于枚举 |
| **类型** | 基本类型/类型转换 | 必须显式转换，无隐式转换 |
| **控制流** | if/for/switch | if可带初始化，for是唯一循环，switch自动break |
| **函数** | 多返回值/defer/闭包 | defer LIFO顺序，闭包捕获变量 |
| **数组** | 固定长度 | 长度是类型的一部分，值类型 |
| **切片** | 动态数组 | make/append/copy，引用类型 |
| **映射** | 键值对 | make创建，delete删除，引用类型 |
| **结构体** | 字段/嵌入/标签 | 匿名嵌入类似继承，标签用于JSON |
| **方法** | 值/指针接收者 | 指针接收者可修改，Go自动转换 |
| **接口** | 隐式实现/空接口 | 无需声明implements，any替代interface{} |
| **错误** | error/panic/recover | 优先用error，defer中recover |
| **并发** | goroutine/channel | goroutine轻量，channel通信 |
| **并发** | select/context | select多路选择，context取消/超时 |
| **并发** | sync包 | Mutex/RWMutex/WaitGroup/Once/Map |
| **包** | module/import | go mod管理，大写公开小写私有 |

---

## 🔗 推荐学习资源

| 资源 | 链接 | 说明 |
|------|------|------|
| Go官方文档 | https://go.dev/doc/ | 最权威的参考 |
| Go by Example | https://gobyexample.com/ | 代码示例驱动学习 |
| Effective Go | https://go.dev/doc/effective_go | 最佳实践指南 |
| Go之旅 | https://tour.go.dev/ | 交互式在线教程 |
| Go标准库文档 | https://pkg.go.dev/std/ | 所有标准包文档 |
| 《Go程序设计语言》 | 书籍 | 经典入门书，C语言作者撰写 |

---

以上就是 Go 语言入门的全面学习资料，从基础语法到并发编程都做了详细讲解。建议按照目录顺序逐步学习，每学完一个知识点就动手写代码练习。**编程最重要的是实践**——光看不练是学不会的！🚀