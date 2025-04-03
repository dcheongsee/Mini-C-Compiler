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
str_2406971856:
.asciiz "Enter amount:\n"
str_2501513638:
.asciiz "Tens:\n"
str_10:
.asciiz "\n"
str_2366411453:
.asciiz "Ones:\n"

.text
.globl main
main:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-20
la $t0,str_2406971856
add $a0,$t0,$zero
li $v0,4
syscall
add $t0,$zero,$zero
li $v0,5
syscall
add $t1,$v0,$zero
addiu $t0,$fp,-12
sw $t1,0($t0)
addiu $t0,$fp,-12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t1,10
lw $t0,0($sp)
addiu $sp,$sp,4
add $t1,$t1,$zero
div $t0,$t1
mflo $t0
addiu $t1,$fp,-16
sw $t0,0($t1)
addiu $t0,$fp,-12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
li $t0,10
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
div $t1,$t0
mfhi $t1
addiu $t0,$fp,-20
sw $t1,0($t0)
la $t0,str_2501513638
add $a0,$t0,$zero
li $v0,4
syscall
add $t0,$zero,$zero
addiu $t0,$fp,-16
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
la $t0,str_2366411453
add $a0,$t0,$zero
li $v0,4
syscall
add $t0,$zero,$zero
addiu $t0,$fp,-20
lw $t0,0($t0)
add $a0,$t0,$zero
li $v0,1
syscall
add $t0,$zero,$zero
label_5_return_main:
addiu $sp,$sp,20
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
li $v0,10
syscall

