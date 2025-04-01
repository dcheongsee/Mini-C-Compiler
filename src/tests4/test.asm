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

.text
.globl main
main:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-32
li $t0,10
addiu $t1,$fp,-12
sw $t0,0($t1)
li $t1,20
addiu $t0,$fp,-16
sw $t1,0($t0)
li $t1,30
addiu $t0,$fp,-20
sw $t1,0($t0)
li $t1,40
addiu $t0,$fp,-24
sw $t1,0($t0)
li $t0,50
addiu $t1,$fp,-28
sw $t0,0($t1)
li $t1,100
addiu $t0,$fp,-32
sw $t1,0($t0)
addiu $t0,$fp,-32
lw $t0,0($t0)
add $a0,$t0,$zero
li $v0,1
syscall
add $t0,$zero,$zero
label_5_return_main:
addiu $sp,$sp,32
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
li $v0,10
syscall

