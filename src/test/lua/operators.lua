function arithmetic(a, b)
    print(a + b)
    print(a - b)
    print(a * b)
    print(a / b)
    print(a // b)
    print(a % b)
    print(a ^ b)
    print(-b)
end

function bitwise(a, b)
    print(a & b)
    print(a | b)
    print(a ~ b)
    print(a >> 4)
    print(a << 4)
    print(~a)
end

function relational(a, b)
    print(a == b)
    print(a ~= b)
    print(a < b)
    print(a > b)
    print(a <= b)
    print(a >= b)
end

function logical(a, b)
    print(a or b)
    print(a and b)
    print(not a)
end

function concatenation(a, b)
    print(a .. b)
end

function length(a)
    print(#a)
end

arithmetic(11, 2)
bitwise(0x0F, 0xF0)
relational(1, 2)
logical(true, false)
concatenation('foo', 'bar')
length('foo')
