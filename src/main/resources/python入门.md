# 🐍 Python 语言入门学习资料（全面版）

---

## 目录

1. [环境安装与配置](#1-环境安装与配置)
2. [基础语法](#2-基础语法)
3. [数据类型详解](#3-数据类型详解)
4. [字符串操作](#4-字符串操作)
5. [列表](#5-列表)
6. [元组](#6-元组)
7. [字典](#7-字典)
8. [集合](#8-集合)
9. [运算符](#9-运算符)
10. [控制流语句](#10-控制流语句)
11. [函数](#11-函数)
12. [面向对象编程](#12-面向对象编程)
13. [异常处理](#13-异常处理)
14. [文件操作](#14-文件操作)
15. [模块与包](#15-模块与包)
16. [迭代器与生成器](#16-迭代器与生成器)
17. [装饰器](#17-装饰器)
18. [正则表达式](#18-正则表达式)
19. [并发编程](#19-并发编程)
20. [常用内置函数](#20-常用内置函数)
21. [常用标准库速览](#21-常用标准库速览)

---

## 1. 环境安装与配置

### 1.1 安装 Python

前往 [python.org](https://www.python.org/) 下载最新稳定版（推荐 3.10+）。

- **Windows**：下载 `.exe` 安装包，安装时勾选 **"Add Python to PATH"**
- **macOS**：可用 `brew install python3` 或官方安装包
- **Linux**：通常自带，或 `sudo apt install python3`

### 1.2 验证安装

```bash
python3 --version   # 应输出如 Python 3.11.5
```

### 1.3 第一段代码

```python
# hello.py
print("Hello, Python!")  # 输出: Hello, Python!
```

运行方式：
```bash
python3 hello.py
```

或在交互式 REPL 中直接输入：
```bash
python3
>>> print("Hello, Python!")
Hello, Python!
```

---

## 2. 基础语法

### 2.1 注释

```python
# 这是单行注释

"""
这是多行注释（实际上是多行字符串，
没被赋值时就当注释用）
"""

'''
也是多行注释，单引号双引号均可
'''
```

### 2.2 变量与赋值

Python 是**动态类型**语言，变量不需要声明类型：

```python
name = "Alice"      # 字符串
age = 25            # 整数
height = 1.68       # 浮点数
is_student = True   # 布尔值

# 多变量同时赋值
x, y, z = 1, 2, 3

# 同值赋给多个变量
a = b = c = 0
```

### 2.3 变量命名规则

| 规则 | 示例 |
|------|------|
| 只能含字母、数字、下划线 | `my_var`, `var_1` ✅；`my-var`, `1var` ❌ |
| 不能以数字开头 | `var1` ✅；`1var` ❌ |
| 不能用关键字 | `for`, `if`, `class` ❌ |
| 区分大小写 | `Name` 和 `name` 是不同变量 |

### 2.4 关键字列表

```python
import keyword
print(keyword.kwlist)
# ['False', 'None', 'True', 'and', 'as', 'assert', 'async', 'await',
#  'break', 'class', 'continue', 'def', 'del', 'elif', 'else', 'except',
#  'finally', 'for', 'from', 'global', 'if', 'import', 'in', 'is',
#  'lambda', 'nonlocal', 'not', 'or', 'pass', 'raise', 'return', 'try',
#  'while', 'with', 'yield']
```

### 2.5 输入与输出

```python
# 输出
print("你好")               # 你好
print("年龄:", 25)           # 年龄: 25
print(f"我叫{name}, {age}岁") # 我叫Alice, 25岁 (f-string格式化)

# 输入
user_input = input("请输入你的名字: ")
print(f"你好, {user_input}!")
```

### 2.6 代码缩进

Python 用**缩进**表示代码块，通常用 **4个空格**：

```python
if True:
    print("缩进正确")     # ← 4个空格
    if True:
        print("更深一层")   # ← 8个空格
print("缩进结束")         # ← 无缩进，属于外层
```

⚠️ **缩进不一致会报 `IndentationError`！不要混用 Tab 和空格。**

---

## 3. 数据类型详解

### 3.1 数值类型

```python
# 整数 int —— 无大小限制
a = 10
b = -3
c = 0b1010    # 二进制 → 10
d = 0o12      # 八进制 → 10
e = 0xA       # 十六进制 → 10

# 浮点数 float
f = 3.14
g = -0.001
h = 2e3       # 2000.0 (科学计数法)

# 复数 complex
z = 3 + 4j
print(z.real)  # 3.0
print(z.imag)  # 4.0
```

### 3.2 类型转换

```python
# 自动转换（小范围 → 大范围）
result = 1 + 2.0   # int + float → float (3.0)

# 强制转换
int_val   = int(3.9)     # 3（截断，不是四舍五入）
float_val = float(10)    # 10.0
str_val   = str(100)     # "100"
bool_val  = bool(0)      # False（0为False，非0为True）
bool_val2 = bool("")     # False（空字符串为False）
bool_val3 = bool("hi")   # True
```

### 3.3 类型查看

```python
x = 42
print(type(x))       # <class 'int'>
print(isinstance(x, int))  # True
```

---

## 4. 字符串操作

### 4.1 创建字符串

```python
s1 = '单引号'
s2 = "双引号"
s3 = """三引号可以
跨多行"""
s4 = r'C:\new\path'  # raw字符串，反斜杠不转义
```

### 4.2 常用操作

```python
s = "Hello, Python!"

# 索引（从0开始，负数从末尾）
print(s[0])     # 'H'
print(s[-1])    # '!'

# 切片 [start:stop:step]
print(s[0:5])    # 'Hello'
print(s[7:])     # 'Python!'
print(s[::-1])   # '!nohtyP ,olleH'（反转）

# 长度
print(len(s))    # 14

# 拼接与重复
print("Hi" + " " + "there")   # 'Hi there'
print("Ha" * 3)                # 'HaHaHa'

# 成员判断
print("Python" in s)    # True
print("Java" not in s)  # True
```

### 4.3 常用方法

```python
s = "  Hello, Python!  "

print(s.strip())          # 'Hello, Python!'   去两端空白
print(s.lstrip())         # 'Hello, Python!  ' 去左空白
print(s.rstrip())         # '  Hello, Python!' 去右空白

print(s.lower())          # '  hello, python!  '
print(s.upper())          # '  HELLO, PYTHON!  '
print(s.title())          # '  Hello, Python!  '（每个单词首字母大写）
print(s.capitalize())     # '  hello, python!  '（仅首字母大写）

print(s.find("Python"))   # 9  （找不到返回-1）
print(s.index("Python"))  # 9  （找不到抛异常）
print(s.count("o"))       # 2
print(s.replace("Python", "World"))  # '  Hello, World!  '

print(s.split(","))       # ['  Hello', ' Python!  ']
print(",".join(["a","b"])) # 'a,b'

print(s.startswith("  H"))  # True
print(s.endswith("!  "))    # True

# 判断类方法
"123".isdigit()     # True
"abc".isalpha()     # True
"abc123".isalnum()  # True
"   ".isspace()     # True
```

### 4.4 字符串格式化

```python
name = "Alice"
age = 25

# 1. f-string（推荐，Python 3.6+）
print(f"My name is {name}, age {age}")
print(f"Next year I'll be {age + 1}")
print(f"{'centered':^20}")       # 居中20字符宽
print(f"{3.14159:.2f}")          # 3.14（保留2位小数）

# 2. str.format()
print("My name is {}, age {}".format(name, age))
print("My name is {0}, age {1}".format(name, age))
print("My name is {n}, age {a}".format(n=name, a=age))

# 3. % 格式化（老式）
print("My name is %s, age %d" % (name, age))
print("Pi is %.2f" % 3.14159)
```

---

## 5. 列表

列表是**有序、可变**的序列，最常用的数据结构。

### 5.1 创建列表

```python
# 直接创建
nums = [1, 2, 3, 4, 5]
mixed = [1, "hello", 3.14, True]
nested = [[1, 2], [3, 4]]

# 从其他对象创建
list_from_range = list(range(1, 6))   # [1, 2, 3, 4, 5]
list_from_str = list("abc")           # ['a', 'b', 'c']

# 列表推导式（后面详讲）
squares = [x**2 for x in range(5)]    # [0, 1, 4, 9, 16]
```

### 5.2 索引与切片

```python
lst = [10, 20, 30, 40, 50]

print(lst[0])     # 10
print(lst[-1])    # 50
print(lst[1:3])   # [20, 30]
print(lst[:3])    # [10, 20, 30]
print(lst[2:])    # [30, 40, 50]
print(lst[::-1])  # [50, 40, 30, 20, 10]
```

### 5.3 常用操作

```python
lst = [1, 2, 3]

# 修改元素
lst[0] = 10       # [10, 2, 3]

# 添加元素
lst.append(4)           # [10, 2, 3, 4]     末尾添加
lst.insert(1, 99)       # [10, 99, 2, 3, 4] 指定位置插入
lst.extend([5, 6])      # [10, 99, 2, 3, 4, 5, 6]  合并另一个列表

# 删除元素
lst.remove(99)          # 删除第一个值为99的元素
popped = lst.pop()      # 删除并返回最后一个元素 (6)
popped2 = lst.pop(0)    # 删除并返回索引0的元素 (10)
del lst[1]              # 删除索引1的元素

# 查找
print(lst.index(3))     # 返回值3的索引
print(lst.count(3))     # 统计值3出现的次数

# 排序
lst = [3, 1, 4, 1, 5]
lst.sort()              # [1, 1, 3, 4, 5]  原地排序
lst.sort(reverse=True)  # [5, 4, 3, 1, 1]  降序
lst.sort(key=lambda x: -x)  # 也可用key自定义排序规则

# 不修改原列表的排序
new_lst = sorted([3, 1, 4])   # [1, 3, 4]

# 反转
lst.reverse()           # 原地反转
new_lst2 = list(reversed([1,2,3]))  # [3,2,1] 不修改原列表

# 长度
print(len(lst))

# 成员判断
print(3 in lst)       # True
```

### 5.4 列表推导式

```python
# 基本形式：[表达式 for 变量 in 可迭代对象]
squares = [x**2 for x in range(5)]
# [0, 1, 4, 9, 16]

# 加条件过滤
evens = [x for x in range(10) if x % 2 == 0]
# [0, 2, 4, 6, 8]

# 双重循环
pairs = [(x, y) for x in range(3) for y in range(3)]
# [(0,0),(0,1),(0,2),(1,0),(1,1),(1,2),(2,0),(2,1),(2,2)]

# 嵌套推导式（处理嵌套列表）
matrix = [[1,2,3],[4,5,6],[7,8,9]]
flat = [num for row in matrix for num in row]
# [1,2,3,4,5,6,7,8,9]
```

### 5.5 列表复制陷阱

```python
a = [1, 2, 3]

# ⚠️ 浅拷贝：引用同一对象
b = a          # b和a指向同一个列表！
b[0] = 99
print(a)       # [99, 2, 3]  a也变了！

# ✅ 正确的拷贝方式
c = a.copy()          # 方法1
d = list(a)           # 方法2
e = a[:]              # 方法3（切片）
# 修改c/d/e不会影响a
```

---

## 6. 元组

元组是**有序、不可变**的序列。一旦创建，元素不能增删改。

### 6.1 创建元组

```python
# 直接创建
t1 = (1, 2, 3)
t2 = 1, 2, 3          # 括号可省略
t3 = ()               # 空元组
t4 = (1,)             # ⚠️ 单元素元组必须加逗号！
t5 = tuple([1, 2, 3]) # 从列表转换
t6 = tuple("abc")     # ('a', 'b', 'c') 从字符串转换
t7 = tuple(range(3))  # (0, 1, 2) 从range转换

# 注意：不加逗号就不是元组！
not_tuple = (1)        # 这只是整数 1，不是元组
is_tuple  = (1,)       # 这才是元组
```

### 6.2 元组的基本操作

```python
t = (10, 20, 30, 40, 50)

# 索引
print(t[0])     # 10
print(t[-1])    # 50

# 切片
print(t[1:3])   # (20, 30)
print(t[::-1])  # (50, 40, 30, 20, 10)

# 长度
print(len(t))   # 5

# 成员判断
print(20 in t)  # True

# 计数和索引
print(t.count(20))   # 1
print(t.index(30))   # 2

# 拼接与重复（创建新元组，原元组不变）
t2 = t + (60, 70)     # (10, 20, 30, 40, 50, 60, 70)
t3 = t * 2            # (10, 20, 30, 40, 50, 10, 20, 30, 40, 50)

# 比较
print((1, 2) < (1, 3))  # True（逐元素比较）

# 最大最小求和
print(max(t))   # 50
print(min(t))   # 10
print(sum(t))   # 150
```

### 6.3 元组不可变的意义

```python
t = (1, 2, 3)
t[0] = 99     # ❌ TypeError: 'tuple' object does not support item assignment
t.append(4)   # ❌ AttributeError: tuple没有append方法

# 但如果元组内含可变对象，那个可变对象本身可以变
t = (1, [2, 3], 4)
t[1].append(5)
print(t)      # (1, [2, 3, 5], 4)  ← 列表部分变了
# 注意：这并不违反元组不可变原则，元组存放的是列表的引用，引用没变
```

### 6.4 元组解包

```python
# 基本解包
t = (1, 2, 3)
a, b, c = t
print(a, b, c)   # 1 2 3

# 星号解包（Python 3+）
t = (1, 2, 3, 4, 5)
first, *middle, last = t
print(first)     # 1
print(middle)    # [2, 3, 4]  ← 注意变成列表
print(last)      # 5

# 交换变量（利用元组解包）
x, y = 10, 20
x, y = y, x
print(x, y)      # 20 10

# 函数返回多个值（本质是返回元组）
def get_info():
    return "Alice", 25

name, age = get_info()
```

### 6.5 元组 vs 列表：何时用元组？

| 特性 | 元组 | 列表 |
|------|------|------|
| 可变性 | ❌ 不可变 | ✅ 可变 |
| 速度 | 更快 | 较慢 |
| 可做字典键 | ✅ 可以 | ❌ 不可以 |
| 可做集合元素 | ✅ 可以 | ❌ 不可以 |
| 语义 | 固定结构/记录 | 动态序列/容器 |

**典型使用场景：**
```python
# 坐标点（固定结构）
point = (3, 4)

# 作为字典的键（列表不能做键）
locations = {(0, 0): "origin", (1, 1): "corner"}

# 数据库记录行
record = ("Alice", 25, "Engineer")

# 函数返回多值
def min_max(data):
    return min(data), max(data)
```

---

## 7. 字典

字典是**键值对**的映射，键必须唯一且不可变。

### 7.1 创建字典

```python
# 直接创建
d1 = {"name": "Alice", "age": 25}

# 从元组列表创建
d2 = dict([(1, "one"), (2, "two")])

# 关键字参数创建
d3 = dict(name="Bob", age=30)

# fromkeys（同值初始化）
d4 = dict.fromkeys(["a", "b", "c"], 0)  # {'a': 0, 'b': 0, 'c': 0}
```

### 7.2 访问与修改

```python
d = {"name": "Alice", "age": 25, "city": "Beijing"}

# 访问
print(d["name"])           # Alice
print(d.get("age"))        # 25
print(d.get("job", "无"))  # 无（键不存在时返回默认值，不报错）

# ⚠️ 键不存在时：
# d["job"]   ← KeyError!
# d.get("job")  ← 返回 None（安全）

# 修改/添加
d["age"] = 26              # 修改已有键
d["job"] = "Engineer"      # 添加新键

# 批量更新
d.update({"age": 27, "hobby": "coding"})

# 删除
del d["city"]               # 删除键
val = d.pop("hobby")        # 删除并返回值
d.popitem()                 # 删除并返回最后一个键值对 (Python 3.7+)

# 清空
d.clear()
```

### 7.3 遍历字典

```python
d = {"name": "Alice", "age": 25, "city": "Beijing"}

# 遍历键
for key in d:
    print(key)

for key in d.keys():
    print(key)

# 遍历值
for value in d.values():
    print(value)

# 遍历键值对（最常用）
for key, value in d.items():
    print(f"{key}: {value}")
# name: Alice
# age: 25
# city: Beijing
```

### 7.4 字典推导式

```python
# 键值翻转
d = {"a": 1, "b": 2}
flipped = {v: k for k, v in d.items()}  # {1: 'a', 2: 'b'}

# 过滤
scores = {"Alice": 85, "Bob": 60, "Cathy": 92}
passed = {k: v for k, v in scores.items() if v >= 70}
# {'Alice': 85, 'Cathy': 92}
```

### 7.5 嵌套字典

```python
students = {
    "Alice": {"age": 25, "grade": "A"},
    "Bob":   {"age": 23, "grade": "B"}
}

print(students["Alice"]["grade"])  # A
```

---

## 8. 集合

集合是**无序、唯一**的元素集合，类似数学中的集合。

### 8.1 创建集合

```python
s1 = {1, 2, 3, 3}          # {1, 2, 3}（自动去重）
s2 = set([1, 2, 2, 3])     # {1, 2, 3}
s3 = set("hello")          # {'h', 'e', 'l', 'o'}（去重）
s4 = set()                  # 空集合 ⚠️ {} 是空字典不是空集合！

# ⚠️ 集合元素必须是不可变类型
# {1, 2, [3, 4]}  ← TypeError！列表不能做集合元素
# {1, 2, (3, 4)}  ← ✅ 元组可以
```

### 8.2 集合操作

```python
s = {1, 2, 3}

# 添加
s.add(4)            # {1, 2, 3, 4}
s.update([5, 6])    # {1, 2, 3, 4, 5, 6}  合并其他可迭代对象

# 删除
s.remove(4)         # 删除元素，不存在则 KeyError
s.discard(99)       # 删除元素，不存在也不报错（推荐）
s.pop()             # 随机删除并返回一个元素
s.clear()           # 清空
```

### 8.3 集合运算（数学集合操作）

```python
a = {1, 2, 3, 4}
b = {3, 4, 5, 6}

# 交集（两者共有）
print(a & b)            # {3, 4}
print(a.intersection(b))  # {3, 4}

# 并集（两者合并去重）
print(a | b)            # {1, 2, 3, 4, 5, 6}
print(a.union(b))       # {1, 2, 3, 4, 5, 6}

# 差集（a有但b没有）
print(a - b)            # {1, 2}
print(a.difference(b))  # {1, 2}

# 对称差集（只在其中一个集合出现）
print(a ^ b)                    # {1, 2, 5, 6}
print(a.symmetric_difference(b)) # {1, 2, 5, 6}

# 子集/超集判断
c = {1, 2}
print(c <= a)           # True（c是a的子集）
print(c.issubset(a))    # True
print(a >= c)           # True（a是c的超集）
print(a.issuperset(c))  # True
```

### 8.4 集合推导式

```python
s = {x**2 for x in range(-3, 4)}
# {0, 1, 4, 9}（负数的平方和正数相同，自动去重）
```

---

## 9. 运算符

### 9.1 算术运算符

```python
print(10 + 3)    # 13   加
print(10 - 3)    # 7    减
print(10 * 3)    # 30   乘
print(10 / 3)    # 3.333...  除（浮点）
print(10 // 3)   # 3    整除（地板除）
print(10 % 3)    # 1    取模（余数）
print(2 ** 3)    # 8    幂运算
```

### 9.2 比较运算符

```python
print(5 == 5)    # True   等于
print(5 != 3)    # True   不等于
print(5 > 3)     # True   大于
print(5 < 3)     # False  小于
print(5 >= 5)    # True   大于等于
print(5 <= 3)    # False  小于等于
```

### 9.3 逻辑运算符

```python
print(True and False)   # False
print(True or False)    # True
print(not True)         # False

# 短路求值
x = 0
print(x != 0 and 10 / x > 1)  # False（and左边为False，右边不执行）

# 链式比较
print(1 < 2 < 3)   # True  等价于 1 < 2 and 2 < 3
```

### 9.4 位运算符

```python
print(5 & 3)    # 1    按位与     101 & 011 = 001
print(5 | 3)    # 7    按位或     101 | 011 = 111
print(5 ^ 3)    # 6    按位异或   101 ^ 011 = 110
print(~5)       # -6   按位取反
print(5 << 1)   # 10   左移      101 → 1010
print(5 >> 1)   # 2    右移      101 → 010
```

### 9.5 成员与身份运算符

```python
# 成员运算符
print(3 in [1, 2, 3])      # True
print(4 not in [1, 2, 3])  # True

# 身份运算符（判断是否是同一对象）
a = [1, 2]
b = [1, 2]
c = a
print(a is c)      # True（同一对象）
print(a is b)      # False（不同对象，即使值相同）
print(a == b)      # True（值相等）
print(a is not b)  # True
```

### 9.6 赋值运算符

```python
x = 10
x += 5    # x = 15   等价于 x = x + 5
x -= 3    # x = 12
x *= 2    # x = 24
x //= 3   # x = 8
x %= 3    # x = 2
x **= 4   # x = 16
x &= 3    # x = 0
x |= 5    # x = 5
```

---

## 10. 控制流语句

### 10.1 if 条件语句

```python
age = 18

if age < 12:
    print("儿童")
elif age < 18:
    print("少年")
else:
    print("成年")

# 三元表达式
status = "成年" if age >= 18 else "未成年"

# match-case（Python 3.10+，类似 switch-case）
command = "start"
match command:
    case "start":
        print("启动")
    case "stop":
        print("停止")
    case _:
        print("未知命令")   # _ 是通配
```

### 10.2 for 循环

```python
# 遍历列表
for item in [1, 2, 3]:
    print(item)

# 遍历范围
for i in range(5):       # 0, 1, 2, 3, 4
    print(i)

for i in range(2, 10, 3): # 2, 5, 8（步长3）
    print(i)

# 遍历字典
for key, value in {"a": 1, "b": 2}.items():
    print(key, value)

# 遍历字符串
for ch in "hello":
    print(ch)

# enumerate（同时获取索引和值）
fruits = ["apple", "banana", "cherry"]
for index, fruit in enumerate(fruits):
    print(f"{index}: {fruit}")
# 0: apple
# 1: banana
# 2: cherry

# zip（同时遍历多个序列）
names = ["Alice", "Bob"]
ages = [25, 30]
for name, age in zip(names, ages):
    print(f"{name} is {age}")

# break 和 continue
for i in range(10):
    if i == 3:
        continue    # 跳过3
    if i == 7:
        break       # 到7就停止
    print(i)        # 0,1,2,4,5,6

# for-else（循环正常结束才执行else）
for n in range(2, 10):
    if n == 5:
        break
else:
    print("循环未被break打断")  # 不会执行
```

### 10.3 while 循环

```python
count = 0
while count < 5:
    print(count)
    count += 1

# while-else
n = 0
while n < 5:
    n += 1
else:
    print("循环正常结束")  # 会执行

# ⚠️ 避免无限循环！确保条件最终为False
```

---

## 11. 函数

### 11.1 定义与调用

```python
def greet(name):
    """打招呼函数"""          # docstring（文档字符串）
    return f"Hello, {name}!"

result = greet("Alice")
print(result)                 # Hello, Alice!
print(greet.__doc__)          # 打招呼函数
```

### 11.2 参数类型

```python
# 1. 位置参数
def add(a, b):
    return a + b
print(add(1, 2))     # 3

# 2. 默认参数
def greet(name, greeting="Hello"):
    return f"{greeting}, {name}!"
print(greet("Alice"))            # Hello, Alice!
print(greet("Alice", "Hi"))      # Hi, Alice!

# ⚠️ 默认参数不要用可变对象！
def bad_append(item, lst=[]):    # ❌ lst每次调用共享同一列表
    lst.append(item)
    return lst

def good_append(item, lst=None): # ✅
    if lst is None:
        lst = []
    lst.append(item)
    return lst

# 3. 关键字参数（调用时指定参数名）
def profile(name, age, city):
    return f"{name}, {age}, {city}"
print(profile(name="Alice", city="Beijing", age=25))

# 4. *args（可变位置参数）
def sum_all(*numbers):
    return sum(numbers)
print(sum_all(1, 2, 3, 4))  # 10

# 5. **kwargs（可变关键字参数）
def show_info(**kwargs):
    for key, value in kwargs.items():
        print(f"{key}: {value}")
show_info(name="Alice", age=25)
# name: Alice
# age: 25

# 6. 混合使用（顺序：位置→*args→关键字→**kwargs）
def func(a, b, *args, c=0, **kwargs):
    print(a, b, args, c, kwargs)
func(1, 2, 3, 4, c=5, d=6)  # 1 2 (3, 4) 5 {'d': 6}
```

### 11.3 返回值

```python
# 单返回值
def square(x):
    return x ** 2

# 多返回值（返回元组）
def min_max(lst):
    return min(lst), max(lst)

lo, hi = min_max([1, 5, 3])  # 解包

# 无return则返回None
def say_hi():
    print("Hi")

result = say_hi()
print(result)  # None
```

### 11.4 匿名函数

```python
# 语法：lambda 参数: 表达式
square = lambda x: x ** 2
print(square(5))  # 25

# 常见用法：排序、过滤
students = [("Alice", 85), ("Bob", 60), ("Cathy", 92)]
students.sort(key=lambda s: s[1])  # 按分数排序

nums = [1, 2, 3, 4, 5]
evens = list(filter(lambda x: x % 2 == 0, nums))  # [2, 4]
doubles = list(map(lambda x: x * 2, nums))         # [2, 4, 6, 8, 10]
```

### 11.5 作用域

```python
x = 10  # 全局变量

def func():
    x = 20  # 局部变量（不影响全局x）
    print(x)

func()     # 20
print(x)   # 10

# 修改全局变量需用 global
def modify_global():
    global x
    x = 99

modify_global()
print(x)   # 99

# 嵌套函数中的 nonlocal
def outer():
    count = 0
    def inner():
        nonlocal count
        count += 1
        return count
    return inner

counter = outer()
print(counter())  # 1
print(counter())  # 2
```

### 11.6 递归

```python
# 斐波那契数列
def fib(n):
    if n <= 1:
        return n
    return fib(n - 1) + fib(n - 2)

print(fib(10))  # 55

# ⚠️ 递归深度有限制，默认1000
# 优化：用缓存或改写为迭代
```

---

## 12. 面向对象编程（OOP）

### 12.1 类与对象

```python
class Dog:
    """狗类"""

    # 类属性（所有实例共享）
    species = "犬科动物"

    # 构造方法（实例化时自动调用）
    def __init__(self, name, age):
        self.name = name    # 实例属性
        self.age = age

    # 实例方法
    def bark(self):
        return f"{self.name} says: Woof!"

    def __str__(self):       # print()时调用
        return f"Dog({self.name}, {self.age})"

    def __repr__(self):      # repr()时调用
        return f"Dog('{self.name}', {self.age})"


# 创建实例
dog1 = Dog("Buddy", 3)
dog2 = Dog("Max", 5)

print(dog1.bark())          # Buddy says: Woof!
print(dog1)                 # Dog(Buddy, 3)
print(Dog.species)          # 犬科动物（通过类名访问类属性）
```

### 12.2 访问控制

```python
class BankAccount:
    def __init__(self, owner, balance):
        self.owner = owner          # 公开
        self._balance = balance     # 受保护（约定，不强制）
        self.__secret = "password"  # 私有（名称改写，较难直接访问）

    def get_balance(self):
        return self._balance

    # 访问私有属性（通过名称改写 _ClassName__attr）
    # self.__secret 实际变成了 self._BankAccount__secret

account = BankAccount("Alice", 1000)
print(account.owner)         # Alice
print(account._balance)      # 1000（可以访问，但不推荐）
# print(account.__secret)    # ❌ AttributeError
print(account._BankAccount__secret)  # "password"（能访问但不推荐）
```

### 12.3 属性装饰器

```python
class Circle:
    def __init__(self, radius):
        self._radius = radius

    @property                # 把方法当属性用
    def radius(self):
        return self._radius

    @radius.setter           # 设置时调用
    def radius(self, value):
        if value < 0:
            raise ValueError("半径不能为负")
        self._radius = value

    @radius.deleter          # 删除时调用
    def radius(self):
        del self._radius

    @property
    def area(self):
        return 3.14159 * self._radius ** 2


c = Circle(5)
print(c.radius)   # 5（像属性一样访问，实际调用了getter方法）
c.radius = 10     # 调用了setter
print(c.area)     # 314.159
# c.area = 100    # ❌ 没有setter，不能设置
```

### 12.4 继承

```python
class Animal:
    def __init__(self, name):
        self.name = name

    def speak(self):
        raise NotImplementedError("子类必须实现speak方法")


class Dog(Animal):        # Dog继承Animal
    def speak(self):
        return f"{self.name} says Woof!"


class Cat(Animal):        # Cat继承Animal
    def speak(self):
        return f"{self.name} says Meow!"


dog = Dog("Buddy")
cat = Cat("Whiskers")
print(dog.speak())  # Buddy says Woof!
print(cat.speak())  # Whiskers says Meow!

# isinstance 和 issubclass
print(isinstance(dog, Animal))   # True
print(isinstance(dog, Dog))      # True
print(issubclass(Dog, Animal))   # True
```

### 12.5 多继承与 MRO

```python
class A:
    def method(self):
        print("A")

class B(A):
    def method(self):
        print("B")

class C(A):
    def method(self):
        print("C")

class D(B, C):       # 多继承
    pass

d = D()
d.method()           # B（按MRO顺序：D→B→C→A）
print(D.__mro__)     # (<class 'D'>, <class 'B'>, <class 'C'>, <class 'A'>, <class 'object'>)

# super() 按MRO调用下一个类
class D(B, C):
    def method(self):
        super().method()  # 调用B.method()（MRO中D的下一个是B）
```

### 12.6 静态方法与类方法

```python
class MathUtils:
    PI = 3.14159

    @staticmethod           # 不需要self或cls
    def add(a, b):
        return a + b

    @classmethod            # 接收cls（类本身）
    def circle_area(cls, radius):
        return cls.PI * radius ** 2


print(MathUtils.add(1, 2))        # 3
print(MathUtils.circle_area(5))   # 78.53975
```

### 12.7 常用魔术方法

```python
class Vector:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __add__(self, other):           # + 运算符
        return Vector(self.x + other.x, self.y + other.y)

    def __sub__(self, other):           # - 运算符
        return Vector(self.x - other.x, self.y - other.y)

    def __mul__(self, scalar):          # * 运算符
        return Vector(self.x * scalar, self.y * scalar)

    def __eq__(self, other):            # == 运算符
        return self.x == other.x and self.y == other.y

    def __len__(self):                  # len()
        return int((self.x**2 + self.y**2)**0.5)

    def __str__(self):                  # str() / print()
        return f"Vector({self.x}, {self.y})"

    def __repr__(self):                 # repr()
        return f"Vector({self.x}, {self.y})"

    def __getitem__(self, index):       # [] 访问
        if index == 0: return self.x
        if index == 1: return self.y
        raise IndexError

    def __iter__(self):                 # 可迭代
        yield self.x
        yield self.y

v1 = Vector(1, 2)
v2 = Vector(3, 4)
print(v1 + v2)      # Vector(4, 6)
print(v1 * 3)       # Vector(3, 6)
print(len(v1))      # 2
```

---

## 13. 异常处理

### 13.1 常见异常类型

| 异常 | 说明 |
|------|------|
| `SyntaxError` | 语法错误 |
| `NameError` | 变量未定义 |
| `TypeError` | 类型不匹配 |
| `ValueError` | 值不合法 |
| `IndexError` | 索引越界 |
| `KeyError` | 字典键不存在 |
| `ZeroDivisionError` | 除以零 |
| `FileNotFoundError` | 文件不存在 |
| `AttributeError` | 属性不存在 |
| `ImportError` | 导入失败 |

### 13.2 try-except

```python
try:
    result = 10 / 0
except ZeroDivisionError:
    print("不能除以零！")

# 捕获多种异常
try:
    lst = [1, 2]
    print(lst[10])
except (IndexError, KeyError) as e:
    print(f"错误: {e}")

# 分层捕获
try:
    int("abc")
except ValueError:
    print("值错误")
except TypeError:
    print("类型错误")

# 捕获所有异常（慎用）
try:
    some_code()
except Exception as e:
    print(f"发生异常: {e}")
```

### 13.3 完整结构

```python
try:
    f = open("data.txt")
    data = f.read()
except FileNotFoundError:
    print("文件不存在")
except PermissionError:
    print("权限不足")
else:
    # try 没有异常时执行
    print(f"读取成功: {len(data)}字符")
finally:
    # 无论是否异常都执行（常用于资源清理）
    if 'f' in dir() and not f.closed:
        f.close()
```

### 13.4 自定义异常

```python
class InvalidAgeError(Exception):
    """年龄不合法异常"""
    def __init__(self, age, message="年龄必须在0-150之间"):
        self.age = age
        self.message = message
        super().__init__(self.message)

    def __str__(self):
        return f"{self.message} (got {self.age})"


def set_age(age):
    if age < 0 or age > 150:
        raise InvalidAgeError(age)
    print(f"年龄设置为: {age}")

try:
    set_age(200)
except InvalidAgeError as e:
    print(e)  # 年龄必须在0-150之间
```

### 13.5 assert 断言

```python
def sqrt(x):
    assert x >= 0, "不能对负数开方"  # 条件为False时抛AssertionError
    return x ** 0.5
```

---

## 14. 文件操作

### 14.1 读写文本文件

```python
# 写文件
with open("hello.txt", "w", encoding="utf-8") as f:
    f.write("Hello\n")
    f.write("World\n")
    f.writelines(["Line1\n", "Line2\n"])

# 读文件（推荐用with，自动关闭文件）
with open("hello.txt", "r", encoding="utf-8") as f:
    content = f.read()           # 读取全部内容（字符串）
    # content = f.readline()     # 读取一行
    # lines = f.readlines()      # 读取所有行（列表）

# 逐行读取（大文件推荐）
with open("hello.txt", "r", encoding="utf-8") as f:
    for line in f:               # 内存友好
        print(line.strip())
```

### 14.2 文件模式

| 模式 | 说明 |
|------|------|
| `"r"` | 读（默认） |
| `"w"` | 写（清空后写） |
| `"a"` | 追加 |
| `"x"` | 独占创建（文件已存在则报错） |
| `"rb"` | 二进制读 |
| `"wb"` | 二进制写 |
| `"r+"` | 读写 |
| `"w+"` | 写读（清空） |
| `"a+"` | 追加读 |

### 14.3 文件路径操作

```python
import os

# 当前工作目录
print(os.getcwd())

# 改变工作目录
os.chdir("/tmp")

# 列出目录内容
print(os.listdir("."))

# 创建/删除目录
os.mkdir("new_dir")
os.makedirs("parent/child", exist_ok=True)  # 递归创建
os.remove("hello.txt")       # 删除文件
os.rmdir("new_dir")          # 删除空目录

# 路径拼接（推荐用pathlib）
from pathlib import Path
p = Path("docs") / "notes.txt"   # docs/notes.txt（跨平台）
print(p.exists())
print(p.is_file())
print(p.parent)                   # docs
print(p.stem)                     # notes（文件名不含扩展名）
print(p.suffix)                   # .txt
```

### 14.4 CSV 文件

```python
import csv

# 写CSV
with open("data.csv", "w", newline="", encoding="utf-8") as f:
    writer = csv.writer(f)
    writer.writerow(["姓名", "年龄"])
    writer.writerow(["Alice", 25])
    writer.writerow(["Bob", 30])

# 读CSV
with open("data.csv", "r", encoding="utf-8") as f:
    reader = csv.reader(f)
    for row in reader:
        print(row)  # ['姓名', '年龄'], ['Alice', '25'], ['Bob', '30']

# 用DictReader/DictWriter（更方便）
with open("data.csv", "r", encoding="utf-8") as f:
    reader = csv.DictReader(f)
    for row in reader:
        print(row["姓名"], row["年龄"])
```

### 14.5 JSON 文件

```python
import json

data = {"name": "Alice", "scores": [85, 92, 78]}

# 写JSON
with open("data.json", "w", encoding="utf-8") as f:
    json.dump(data, f, ensure_ascii=False, indent=2)

# 读JSON
with open("data.json", "r", encoding="utf-8") as f:
    loaded = json.load(f)
    print(loaded)  # {'name': 'Alice', 'scores': [85, 92, 78]}

# 字符串 ↔ JSON
json_str = json.dumps(data, ensure_ascii=False)
parsed = json.loads(json_str)
```

---

## 15. 模块与包

### 15.1 导入模块

```python
# 导入整个模块
import math
print(math.sqrt(16))    # 4.0

# 导入特定函数
from math import pi, sqrt
print(pi)               # 3.141592653589793
print(sqrt(9))          # 3.0

# 导入并重命名
import numpy as np
from math import sqrt as msqrt

# 导入所有（慎用，可能命名冲突）
from math import *      # 不推荐

# 相对导入（在包内部使用）
from . import submodule       # 同级
from .. import parentmodule   # 上级
```

### 15.2 创建自己的模块

```python
# mymodule.py
def greet(name):
    return f"Hello, {name}!"

PI = 3.14159

if __name__ == "__main__":
    # 只在本文件直接运行时执行，被导入时不执行
    print(greet("Test"))
```

```python
# main.py
import mymodule
print(mymodule.greet("Alice"))   # Hello, Alice!
print(mymodule.PI)                # 3.14159
```

### 15.3 创建包

```
mypackage/
    __init__.py          # 包初始化文件（可以为空）
    module1.py
    module2.py
    subpackage/
        __init__.py
        module3.py
```

```python
# 使用包
import mypackage.module1
from mypackage import module2
from mypackage.subpackage.module3 import SomeClass
```

### 15.4 常用第三方包管理（pip）

```bash
# 安装包
pip install requests

# 卸载
pip uninstall requests

# 查看已安装
pip list

# 查看包信息
pip show requests

# 导出依赖
pip freeze > requirements.txt

# 从依赖文件安装
pip install -r requirements.txt

# 更新包
pip install --upgrade requests
```

---

## 16. 迭代器与生成器

### 16.1 迭代器

```python
# 可迭代对象：能返回迭代器的对象（list, str, dict等）
# 迭代器：实现了 __iter__() 和 __next__() 的对象

lst = [1, 2, 3]
it = iter(lst)       # 获取迭代器

print(next(it))      # 1
print(next(it))      # 2
print(next(it))      # 3
# print(next(it))    # StopIteration异常

# 自定义迭代器
class CountDown:
    def __init__(self, start):
        self.count = start

    def __iter__(self):
        return self

    def __next__(self):
        if self.count <= 0:
            raise StopIteration
        self.count -= 1
        return self.count + 1

for num in CountDown(5):
    print(num)   # 5, 4, 3, 2, 1
```

### 16.2 生成器

生成器是**特殊的迭代器**，用 `yield` 代替 `return`，每次 `yield` 暂停，下次从暂停处继续。

```python
# 生成器函数
def my_range(start, end):
    current = start
    while current < end:
        yield current        # 暂停并返回值
        current += 1

for num in my_range(0, 5):
    print(num)   # 0, 1, 2, 3, 4

# 生成器表达式（类似列表推导式，但用圆括号）
squares_gen = (x**2 for x in range(5))
print(list(squares_gen))  # [0, 1, 4, 9, 16]

# 生成器优势：惰性计算，节省内存
# 对比：列表推导式立即创建所有元素，生成器逐个产出
```

### 16.3 yield from（委托生成器）

```python
def chain(*iterables):
    for it in iterables:
        yield from it     # 直接产出子迭代器的所有值

list(chain([1,2], [3,4], [5]))  # [1, 2, 3, 4, 5]
```

---

## 17. 装饰器

装饰器是**修改函数行为的函数**，本质是高阶函数。

### 17.1 基本装饰器

```python
def timer(func):
    """计算函数执行时间的装饰器"""
    import time
    def wrapper(*args, **kwargs):
        start = time.time()
        result = func(*args, **kwargs)
        end = time.time()
        print(f"{func.__name__} 执行耗时: {end - start:.4f}秒")
        return result
    return wrapper

@timer              # 等价于 slow_func = timer(slow_func)
def slow_func():
    import time
    time.sleep(1)
    return "完成"

result = slow_func()
# slow_func 执行耗时: 1.0012秒
```

### 17.2 带参数的装饰器

```python
def repeat(n):          # 装饰器工厂
    def decorator(func):  # 装饰器
        def wrapper(*args, **kwargs):
            for _ in range(n):
                result = func(*args, **kwargs)
            return result
        return wrapper
    return decorator

@repeat(3)            # 等价于 greet = repeat(3)(greet)
def greet(name):
    print(f"Hello, {name}!")

greet("Alice")        # 打印3次 Hello, Alice!
```

### 17.3 functools.wraps（保留原函数信息）

```python
from functools import wraps

def my_decorator(func):
    @wraps(func)              # ← 重要！保留func的__name__等信息
    def wrapper(*args, **kwargs):
        print("调用前")
        result = func(*args, **kwargs)
        print("调用后")
        return result
    return wrapper

@my_decorator
def my_func():
    """这是我的函数"""
    pass

print(my_func.__name__)   # my_func（不加wraps则显示wrapper）
print(my_func.__doc__)    # 这是我的函数
```

### 17.4 类装饰器

```python
class CountCalls:
    """统计函数调用次数"""
    def __init__(self, func):
        self.func = func
        self.count = 0

    def __call__(self, *args, **kwargs):  # 实例被调用时执行
        self.count += 1
        print(f"{self.func.__name__} 已调用{self.count}次")
        return self.func(*args, **kwargs)

@CountCalls
def say_hi():
    print("Hi!")

say_hi()   # say_hi 已调用1次 / Hi!
say_hi()   # say_hi 已调用2次 / Hi!
```

### 17.5 常用内置装饰器

```python
class MyClass:
    @staticmethod
    def static_method():
        print("静态方法，不需要self")

    @classmethod
    def class_method(cls):
        print(f"类方法，cls是{cls}")

    @property
    def my_prop(self):
        return self._value
```

---

## 18. 正则表达式

```python
import re

# 1. 基本匹配
pattern = r"hello"          # r前缀：raw字符串，反斜杠不转义
text = "say hello world"
match = re.search(pattern, text)
if match:
    print(match.group())     # hello
    print(match.start())     # 4（起始位置）
    print(match.end())       # 9

# 2. 匹配全部
results = re.findall(r"\d+", "a12b34c56")
print(results)               # ['12', '34', '56']

# 3. 替换
new_text = re.sub(r"\d+", "NUM", "a12b34c56")
print(new_text)              # aNUMbNUMcNUM

# 4. 分割
parts = re.split(r"[,;]", "a,b;c")
print(parts)                 # ['a', 'b', 'c']

# 5. 编译正则（多次使用时更高效）
regex = re.compile(r"\b\w+@\w+\.\w+\b")  # 简单邮箱匹配
emails = regex.findall("test@a.com and hello@b.org")
print(emails)  # ['test@a.com', 'hello@b.org']

# 6. 常用元字符
"""
.       任意字符（除换行）
\d      数字 [0-9]
\w      字母数字下划线
\s      空白字符
^       开头
$       结尾
*       0次或多次
+       1次或多次
?       0次或1次
{n}     恰好n次
{n,m}   n到m次
[]      字符集
|       或
()      分组
"""

# 7. 分组
text = "2024-01-15"
match = re.match(r"(\d{4})-(\d{2})-(\d{2})", text)
if match:
    print(match.group(0))   # 2024-01-15（完整匹配）
    print(match.group(1))   # 2024（第1组）
    print(match.group(2))   # 01（第2组）
    print(match.groups())   # ('2024', '01', '15')

# 命名分组
match = re.match(r"(?P<year>\d{4})-(?P<month>\d{2})", "2024-01")
if match:
    print(match.group("year"))   # 2024
```

---

## 19. 并发编程 ⭐

这是 Python 进阶重点，涵盖多线程、多进程和异步编程。

### 19.1 并发概念速览

| 概念 | 说明 |
|------|------|
| **并发** | 多个任务交替执行，看起来同时进行 |
| **并行** | 多个任务真正同时执行（需要多核CPU） |
| **线程** | 进程内的执行单元，共享内存 |
| **进程** | 独立的程序实例，内存隔离 |
| **协程** | 用户态的轻量级"线程"，由程序控制切换 |

Python 的全局解释器锁（**GIL**）使得多线程**不能真正并行执行CPU密集型任务**，因此：
- **I/O密集型** → 用多线程 或 异步
- **CPU密集型** → 用多进程

---

### 19.2 多线程

#### 19.2.1 基本用法

```python
import threading
import time

def worker(name, delay):
    print(f"{name} 开始工作")
    time.sleep(delay)          # 模拟I/O等待
    print(f"{name} 完成，耗时{delay}秒")

# 创建线程
t1 = threading.Thread(target=worker, args=("线程A", 2))
t2 = threading.Thread(target=worker, args=("线程B", 3))

# 启动线程
t1.start()
t2.start()

# 等待线程完成（阻塞主线程）
t1.join()
t2.join()

print("所有线程已完成")
```

#### 19.2.2 继承 Thread 类

```python
class MyThread(threading.Thread):
    def __init__(self, name, delay):
        super().__init__()
        self.name = name
        self.delay = delay

    def run(self):              # 重写run方法
        print(f"{self.name} 开始")
        time.sleep(self.delay)
        print(f"{self.name} 完成")

t = MyThread("自定义线程", 2)
t.start()
t.join()
```

#### 19.2.3 线程共享数据与锁

多个线程同时修改共享数据可能导致**数据竞争**：

```python
# ❌ 不安全：没有锁
counter = 0

def unsafe_increment():
    global counter
    for _ in range(100000):
        counter += 1     # 不是原子操作！可能被其他线程打断

threads = [threading.Thread(target=unsafe_increment) for _ in range(5)]
for t in threads: t.start()
for t in threads: t.join()
print(counter)   # 可能不是500000（小于500000）
```

```python
# ✅ 安全：使用锁
counter = 0
lock = threading.Lock()          # 创建锁

def safe_increment():
    global counter
    for _ in range(100000):
        with lock:               # with语句自动加锁/解锁
            counter += 1

threads = [threading.Thread(target=safe_increment) for _ in range(5)]
for t in threads: t.start()
for t in threads: t.join()
print(counter)   # 500000（正确！）
```

#### 19.2.4 其他同步工具

```python
import threading

# RLock（可重入锁）—— 同一线程可以多次acquire
rlock = threading.RLock()
with rlock:
    with rlock:       # 同一线程，不会死锁
        print("嵌套加锁成功")

# Semaphore（信号量）—— 控制同时访问的数量
sem = threading.Semaphore(3)   # 最多3个线程同时访问

def limited_access():
    with sem:
        print(f"{threading.current_thread().name} 正在访问")
        time.sleep(1)

# Event（事件）—— 线程间通知
event = threading.Event()

def waiter():
    print("等待事件...")
    event.wait()               # 阻塞直到事件被set
    print("事件已触发！")

def setter():
    time.sleep(2)
    event.set()                # 触发事件

threading.Thread(target=waiter).start()
threading.Thread(target=setter).start()

# Condition（条件变量）—— 更复杂的等待/通知
cond = threading.Condition()

# Barrier（栅栏）—— 所有线程到达后一起继续
barrier = threading.Barrier(3)  # 3个线程都到达后继续
```

#### 19.2.5 线程池

```python
from concurrent.futures import ThreadPoolExecutor

def task(name):
    print(f"{name} 开始")
    time.sleep(1)
    return f"{name} 完成"

# 创建线程池
with ThreadPoolExecutor(max_workers=3) as executor:
    # 提交单个任务
    future = executor.submit(task, "任务1")
    print(future.result())           # 任务1 完成

    # 批量提交
    results = executor.map(task, ["任务A", "任务B", "任务C"])
    for result in results:
        print(result)

    # submit + as_completed（按完成顺序获取结果）
    futures = [executor.submit(task, f"任务{i}") for i in range(5)]
    for future in concurrent.futures.as_completed(futures):
        print(future.result())
```

---

### 19.3 多进程

多进程**绕过GIL**，适合CPU密集型任务。

#### 19.3.1 基本用法

```python
import multiprocessing
import time

def cpu_work(name, duration):
    print(f"进程{name} 开始")
    # 模拟CPU密集型工作
    total = 0
    for i in range(duration * 1000000):
        total += i
    print(f"进程{name} 完成，计算结果: {total}")

if __name__ == "__main__":     # ⚠️ Windows下必须加这行！
    p1 = multiprocessing.Process(target=cpu_work, args=("A", 3))
    p2 = multiprocessing.Process(target=cpu_work, args=("B", 3))

    p1.start()
    p2.start()
    p1.join()
    p2.join()
    print("所有进程已完成")
```

#### 19.3.2 进程间通信

```python
import multiprocessing

# Queue（进程安全队列）
def producer(q):
    for i in range(5):
        q.put(i)
        print(f"生产: {i}")

def consumer(q):
    while True:
        item = q.get()
        print(f"消费: {item}")
        if item == 4:    # 结束信号
            break

if __name__ == "__main__":
    q = multiprocessing.Queue()
    p1 = multiprocessing.Process(target=producer, args=(q,))
    p2 = multiprocessing.Process(target=consumer, args=(q,))
    p1.start()
    p2.start()
    p1.join()
    p2.join()

# Pipe（管道，双向通信）
def sender(conn):
    conn.send("你好！")
    msg = conn.recv()
    print(f"收到回复: {msg}")
    conn.close()

def receiver(conn):
    msg = conn.recv()
    print(f"收到消息: {msg}")
    conn.send("收到！")
    conn.close()

if __name__ == "__main__":
    parent_conn, child_conn = multiprocessing.Pipe()
    p1 = multiprocessing.Process(target=sender, args=(parent_conn,))
    p2 = multiprocessing.Process(target=receiver, args=(child_conn,))
    p1.start()
    p2.start()
    p1.join()
    p2.join()

# Value / Array（共享内存）
def modify(shared_val, shared_arr):
    shared_val.value = 99
    for i in range(len(shared_arr)):
        shared_arr[i] *= 2

if __name__ == "__main__":
    val = multiprocessing.Value('i', 0)       # 共享整数
    arr = multiprocessing.Array('d', [1, 2, 3]) # 共享数组
    p = multiprocessing.Process(target=modify, args=(val, arr))
    p.start()
    p.join()
    print(val.value)   # 99
    print(list(arr))   # [2.0, 4.0, 6.0]
```

#### 19.3.3 进程池

```python
from concurrent.futures import ProcessPoolExecutor
import math

def is_prime(n):
    """判断n是否为质数（CPU密集型）"""
    if n < 2: return False
    for i in range(2, int(math.sqrt(n)) + 1):
        if n % i == 0: return False
    return True

if __name__ == "__main__":
    numbers = range(100000, 100100)

    # 使用进程池并行计算
    with ProcessPoolExecutor(max_workers=4) as executor:
        results = list(executor.map(is_prime, numbers))
        primes = [n for n, is_p in zip(numbers, results) if is_p]
        print(f"质数: {primes}")
```

---

### 19.4 异步编程

Python 3.5+ 引入 `async/await` 语法，适用于**大量I/O操作**（网络请求、数据库等）。

#### 19.4.1 基本概念

```python
import asyncio

# async def 定义协程函数
async def hello():
    print("Hello")
    await asyncio.sleep(1)    # await 暂停，让其他协程运行
    print("World")

# 运行协程
asyncio.run(hello())          # Python 3.7+
```

#### 19.4.2 并发执行多个协程

```python
import asyncio
import time

async def fetch(name, delay):
    print(f"{name} 开始请求")
    await asyncio.sleep(delay)   # 模拟网络请求
    print(f"{name} 完成，耗时{delay}秒")
    return f"{name}的数据"

async def main():
    start = time.time()

    # 并发执行（3个请求同时发出）
    results = await asyncio.gather(
        fetch("A", 2),
        fetch("B", 1),
        fetch("C", 3),
    )
    # 总耗时约3秒（最长的那个），而非2+1+3=6秒！

    print(f"所有结果: {results}")
    print(f"总耗时: {time.time() - start:.1f}秒")

asyncio.run(main())
```

#### 19.4.3 Task 与超时

```python
async def long_task():
    await asyncio.sleep(10)
    return "完成"

async def main():
    # 创建Task（协程立即开始运行）
    task = asyncio.create_task(long_task())

    # 设置超时
    try:
        result = await asyncio.wait_for(task, timeout=3.0)
        print(result)
    except asyncio.TimeoutError:
        print("任务超时！")
        task.cancel()            # 取消任务

asyncio.run(main())
```

#### 19.4.4 异步迭代

```python
async def async_generator():
    for i in range(5):
        await asyncio.sleep(0.1)
        yield i

async def main():
    # async for 遍历异步生成器
    async for num in async_generator():
        print(num)

asyncio.run(main())
```

#### 19.4.5 异步上下文管理器

```python
class AsyncDB:
    async def __aenter__(self):
        print("连接数据库")
        await asyncio.sleep(0.5)
        return self

    async def __aexit__(self, exc_type, exc_val, exc_tb):
        print("关闭连接")

    async def query(self, sql):
        await asyncio.sleep(0.3)
        return f"结果: {sql}"

async def main():
    async with AsyncDB() as db:
        result = await db.query("SELECT * FROM users")
        print(result)

asyncio.run(main())
```

#### 19.4.6 实战：异步HTTP请求（需安装aiohttp）

```python
# pip install aiohttp
import aiohttp
import asyncio

async def fetch_url(session, url):
    async with session.get(url) as response:
        return await response.text()

async def main():
    urls = [
        "https://httpbin.org/get",
        "https://httpbin.org/ip",
        "https://httpbin.org/headers",
    ]

    async with aiohttp.ClientSession() as session:
        tasks = [fetch_url(session, url) for url in urls]
        results = await asyncio.gather(*tasks)
        for url, result in zip(urls, results):
            print(f"{url}: {len(result)}字符")

asyncio.run(main())
```

---

### 19.5 并发方案选择指南

```
                    任务类型判断
                   ┌─────────────┐
                   │  是什么类型？ │
                   └──────┬──────┘
                          │
          ┌───────────────┼───────────────┐
          │               │               │
    I/O密集型         混合型         CPU密集型
    (网络/文件/        (既有I/O        (计算/图像
     数据库等待)        又有计算)        处理/加密)
          │               │               │
   ┌──────┴──────┐   进程池+         多进程
   │             │   线程池          (ProcessPool)
  asyncio     多线程         multiprocessing
(单线程高并发) (ThreadPool)        或
              │             concurrent.futures
         简单场景用          ProcessPoolExecutor
         threading
```

---

## 20. 常用内置函数

```python
# 数学相关
abs(-5)         # 5       绝对值
round(3.14, 1)  # 3.1     四舍五入
min(1, 2, 3)    # 1       最小值
max(1, 2, 3)    # 3       最大值
sum([1, 2, 3])  # 6       求和
pow(2, 3)       # 8       幂运算

# 类型相关
type(42)        # <class 'int'>
int("42")       # 42
float("3.14")   # 3.14
str(42)         # "42"
bool(0)         # False
list("abc")     # ['a', 'b', 'c']
tuple([1,2])    # (1, 2)
dict(a=1)       # {'a': 1}
set([1,1,2])    # {1, 2}

# 序列操作
len([1,2,3])    # 3       长度
sorted([3,1,2]) # [1,2,3] 排序（返回新列表）
reversed([1,2]) # 迭代器  反转
enumerate(["a","b"]) # (0,'a'),(1,'b')
zip([1,2],["a","b"]) # (1,'a'),(2,'b')

# 函数式
map(str, [1,2,3])       # 迭代器: "1","2","3"
filter(lambda x:x>0, [-1,0,1])  # 迭代器: 1
all([True, True])       # True    全为True?
any([True, False])      # True    有True?

# 其他
id(obj)         # 对象的内存地址
hash("abc")     # 哈希值
dir(obj)        # 对象的属性列表
help(func)      # 函数帮助文档
callable(obj)   # 是否可调用
isinstance(obj, cls)  # 类型检查
chr(65)         # 'A'     整数→字符
ord('A')        # 65      字符→整数
bin(10)         # '0b1010'
oct(10)         # '0o12'
hex(10)         # '0xa'

# 可迭代对象拆分
first, *rest = [1, 2, 3, 4, 5]  # first=1, rest=[2,3,4,5]
```

---

## 21. 常用标准库速览

| 库 | 用途 | 简要示例 |
|---|------|---------|
| `os` | 操作系统接口 | `os.listdir(".")` |
| `sys` | 系统相关 | `sys.argv`, `sys.path` |
| `pathlib` | 路径操作（推荐） | `Path("a.txt").read_text()` |
| `re` | 正则表达式 | `re.findall(r"\d+", text)` |
| `json` | JSON处理 | `json.dumps(data)` |
| `csv` | CSV处理 | `csv.reader(f)` |
| `datetime` | 日期时间 | `datetime.now()` |
| `time` | 时间相关 | `time.sleep(1)` |
| `random` | 随机数 | `random.randint(1,10)` |
| `math` | 数学函数 | `math.sqrt(16)` |
| `collections` | 特殊容器 | `Counter`, `defaultdict`, `deque` |
| `itertools` | 迭代器工具 | `itertools.chain`, `permutations` |
| `functools` | 函数工具 | `reduce`, `partial`, `lru_cache` |
| `hashlib` | 哈希/加密 | `hashlib.sha256(data).hexdigest()` |
| `logging` | 日志 | `logging.info("msg")` |
| `unittest` | 单元测试 | `unittest.TestCase` |
| `argparse` | 命令行参数 | `argparse.ArgumentParser()` |
| `threading` | 多线程 | `threading.Thread` |
| `multiprocessing` | 多进程 | `multiprocessing.Process` |
| `asyncio` | 异步编程 | `asyncio.run(coro)` |
| `socket` | 网络 | `socket.socket()` |
| `urllib` | URL处理 | `urllib.request.urlopen(url)` |
| `sqlite3` | SQLite数据库 | `sqlite3.connect("db.sqlite")` |
| `typing` | 类型提示 | `List[int]`, `Optional[str]` |
| `dataclasses` | 数据类 | `@dataclass class Point:` |
| `enum` | 枚举 | `class Color(Enum):` |

---

### 常用标准库示例

```python
# collections —— 特殊容器
from collections import Counter, defaultdict, deque

# Counter（计数器）
c = Counter("abracadabra")
print(c)              # Counter({'a': 5, 'b': 2, 'r': 2, 'c': 1, 'd': 1})
print(c.most_common(2))  # [('a', 5), ('b', 2)]

# defaultdict（带默认值的字典）
d = defaultdict(list)
d["fruits"].append("apple")  # 不需要先初始化空列表
print(d)  # {'fruits': ['apple']}

# deque（双端队列，两端操作O(1)）
dq = deque([1, 2, 3])
dq.appendleft(0)     # deque([0, 1, 2, 3])
dq.pop()             # 3
dq.popleft()         # 0


# itertools —— 迭代器工具
from itertools import chain, permutations, combinations, product

print(list(chain([1,2], [3,4])))              # [1,2,3,4]
print(list(permutations("abc", 2)))           # [('a','b'),('a','c'),('b','a'),...]
print(list(combinations([1,2,3], 2)))         # [(1,2),(1,3),(2,3)]
print(list(product([1,2], ['a','b'])))        # [(1,'a'),(1,'b'),(2,'a'),(2,'b')]


# functools —— 函数工具
from functools import reduce, lru_cache

# reduce（累积运算）
result = reduce(lambda a, b: a + b, [1, 2, 3, 4])  # 10

# lru_cache（缓存，加速递归）
@lru_cache(maxsize=None)
def fib(n):
    if n <= 1: return n
    return fib(n-1) + fib(n-2)

print(fib(100))  # 极快！没有缓存则极慢


# datetime —— 日期时间
from datetime import datetime, timedelta

now = datetime.now()
print(now)                           # 2024-01-15 10:30:00
print(now.strftime("%Y-%m-%d %H:%M")) # 2024-01-15 10:30

tomorrow = now + timedelta(days=1)
print(tomorrow)

# 字符串→日期
dt = datetime.strptime("2024-01-15", "%Y-%m-%d")


# dataclasses —— 数据类（Python 3.7+）
from dataclasses import dataclass

@dataclass
class Point:
    x: float
    y: float
    label: str = "origin"   # 默认值

    def distance(self):
        return (self.x**2 + self.y**2)**0.5

p = Point(3, 4)
print(p)            # Point(x=3.0, y=4.0, label='origin')
print(p.distance()) # 5.0


# typing —— 类型提示
from typing import List, Dict, Optional, Tuple, Union

def process(data: List[int]) -> Dict[str, float]:
    """带类型提示的函数，IDE能更好检查"""
    return {"avg": sum(data) / len(data)}

def find(name: str) -> Optional[str]:
    """可能返回None"""
    return None

def mix(value: Union[int, str]) -> str:
    """可以是int或str"""
    return str(value)
```

---

## 🎯 学习路线建议

```
第1周：基础语法 + 数据类型 + 控制流 + 函数
        ↓
第2周：字符串 + 列表 + 元组 + 字典 + 集合（多练习！）
        ↓
第3周：文件操作 + 异常处理 + 模块/包
        ↓
第4周：面向对象编程（类、继承、魔术方法）
        ↓
第5周：迭代器 + 生成器 + 装饰器（进阶概念）
        ↓
第6周：并发编程（多线程 → 多进程 → asyncio）
        ↓
持续：做项目！爬虫/数据分析/小工具/Web应用...
```

---

## 📚 推荐资源

| 资源 | 说明 |
|------|------|
| [Python官方文档](https://docs.python.org/3/) | 最权威 |
| [Real Python](https://realpython.com/) | 英文教程，质量高 |
| 《Python编程：从入门到实践》 | 经典入门书 |
| 《流畅的Python》 | 进阶必读 |
| [LeetCode Python题解](https://leetcode.cn/) | 练算法 |
| [Python Tutor](https://pythontutor.com/) | 可视化代码执行过程 |

---

> 💡 **学习建议**：每学一个知识点，都动手写代码验证。遇到不懂的用 `help()` 和 `dir()` 查看文档。坚持做小项目，从实践中巩固知识！