.data
.align 2
global_c:
.space 1
.align 2
global_size:
.space 4
.align 2
global_s:
.space 4
.align 2
global_i:
.space 4
str_2254093002:
.asciiz "Entering binomRecHelper: n = "
str_32772984:
.asciiz " k = "
str_10:
.asciiz "\n"
str_3345096528:
.asciiz "Exiting binomRecHelper: n = "
str_3964920270:
.asciiz " returning "

.text
binomRecHelper:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-20
la $t0,str_2254093002
add $a0,$t0,$zero
li $v0,4
syscall
add $t0,$zero,$zero
addiu $t0,$fp,8
lw $t0,0($t0)
add $a0,$t0,$zero
li $v0,1
syscall
add $t0,$zero,$zero
la $t0,str_32772984
add $a0,$t0,$zero
li $v0,4
syscall
add $t0,$zero,$zero
addiu $t0,$fp,12
lw $t0,0($t0)
add $a0,$t0,$zero
li $v0,1
syscall
add $t0,$zero,$zero
la $t0,str_10
add $a0,$t0,$zero
li $v0,4
syscall
add $t0,$zero,$zero
addiu $t0,$fp,12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,0
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
slt $t2,$t1,$t0
slt $t0,$t0,$t1
or $t0,$t2,$t0
xori $t0,$t0,1
beqz $t0,label_9_if_else
li $t0,1
addiu $t1,$fp,-12
sw $t0,0($t1)
j label_10_if_end
label_9_if_else:
addiu $t0,$fp,8
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,12
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
slt $t2,$t1,$t0
slt $t0,$t0,$t1
or $t0,$t2,$t0
xori $t0,$t0,1
beqz $t0,label_11_if_else
li $t1,1
addiu $t0,$fp,-12
sw $t1,0($t0)
j label_12_if_end
label_11_if_else:
addiu $t0,$fp,12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,1
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
sub $t0,$t1,$t0
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,8
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,1
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
sub $t0,$t1,$t0
addiu $sp,$sp,-4
sw $t0,0($sp)
jal binomRecHelper
addiu $sp,$sp,8
add $t1,$v0,$zero
addiu $t0,$fp,-16
sw $t1,0($t0)
addiu $t0,$fp,12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,8
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t1,1
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
sub $t0,$t0,$t1
addiu $sp,$sp,-4
sw $t0,0($sp)
jal binomRecHelper
addiu $sp,$sp,8
add $t0,$v0,$zero
addiu $t1,$fp,-20
sw $t0,0($t1)
addiu $t0,$fp,-16
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-20
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $t1,$fp,-12
sw $t0,0($t1)
label_12_if_end:
label_10_if_end:
la $t0,str_3345096528
add $a0,$t0,$zero
li $v0,4
syscall
add $t0,$zero,$zero
addiu $t0,$fp,8
lw $t0,0($t0)
add $a0,$t0,$zero
li $v0,1
syscall
add $t0,$zero,$zero
la $t0,str_32772984
add $a0,$t0,$zero
li $v0,4
syscall
add $t0,$zero,$zero
addiu $t0,$fp,12
lw $t0,0($t0)
add $a0,$t0,$zero
li $v0,1
syscall
add $t0,$zero,$zero
la $t0,str_3964920270
add $a0,$t0,$zero
li $v0,4
syscall
add $t0,$zero,$zero
addiu $t0,$fp,-12
lw $t0,0($t0)
add $a0,$t0,$zero
li $v0,1
syscall
add $t0,$zero,$zero
la $t0,str_10
add $a0,$t0,$zero
li $v0,4
syscall
add $t0,$zero,$zero
addiu $t0,$fp,-12
lw $t0,0($t0)
add $v0,$t0,$zero
j label_5_return_binomRecHelper
label_5_return_binomRecHelper:
addiu $sp,$sp,20
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
jr $ra

.text
binomRec:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-16
li $t1,25
addiu $t0,$fp,-12
sw $t1,0($t0)
li $t0,12
addiu $t1,$fp,-16
sw $t0,0($t1)
addiu $t0,$fp,-16
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
jal binomRecHelper
addiu $sp,$sp,8
add $t0,$v0,$zero
add $v0,$t0,$zero
j label_16_return_binomRec
label_16_return_binomRec:
addiu $sp,$sp,16
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
jr $ra

.text
.globl main
main:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-12
jal binomRec
addiu $sp,$sp,0
add $t0,$v0,$zero
addiu $t1,$fp,-12
sw $t0,0($t1)
addiu $t0,$fp,-12
lw $t0,0($t0)
add $a0,$t0,$zero
li $v0,1
syscall
add $t0,$zero,$zero
addiu $t0,$fp,-12
lw $t0,0($t0)
add $v0,$t0,$zero
j label_18_return_main
label_18_return_main:
addiu $sp,$sp,12
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
li $v0,10
syscall

