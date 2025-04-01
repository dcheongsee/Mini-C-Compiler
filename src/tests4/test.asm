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
helper:
andi $sp,$sp,-4
# Align sp to word boundary
addiu $sp,$sp,-8
sw $fp,4($sp)
sw $ra,0($sp)
add $fp,$sp,$zero
addiu $sp,$sp,-56
li $t0,1
addiu $t0,$fp,-12
sw $t0,0($t0)
li $t0,2
addiu $t0,$fp,-16
sw $t0,0($t0)
li $t0,3
addiu $t0,$fp,-20
sw $t0,0($t0)
li $t0,4
addiu $t0,$fp,-24
sw $t0,0($t0)
li $t0,5
addiu $t0,$fp,-28
sw $t0,0($t0)
li $t0,6
addiu $t0,$fp,-32
sw $t0,0($t0)
li $t0,7
addiu $t0,$fp,-36
sw $t0,0($t0)
li $t0,8
addiu $t0,$fp,-40
sw $t0,0($t0)
li $t0,9
addiu $t0,$fp,-44
sw $t0,0($t0)
li $t0,10
addiu $t0,$fp,-48
sw $t0,0($t0)
li $t0,11
addiu $t0,$fp,-52
sw $t0,0($t0)
addiu $t0,$fp,-12
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-16
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-20
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-24
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-28
lw $t1,0($t0)
lw $t0,0($sp)
addiu $sp,$sp,4
add $t0,$t1,$zero
add $t0,$t0,$t0
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-32
lw $t1,0($t0)
lw $t0,0($sp)
addiu $sp,$sp,4
add $t0,$t1,$zero
add $t0,$t0,$t0
addiu $t0,$fp,-56
sw $t0,0($t0)
addiu $t0,$fp,-56
lw $t0,0($t0)
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-36
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-40
lw $t1,0($t0)
lw $t0,0($sp)
addiu $sp,$sp,4
add $t0,$t1,$zero
add $t0,$t0,$t0
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-44
lw $t1,0($t0)
lw $t0,0($sp)
addiu $sp,$sp,4
add $t0,$t1,$zero
add $t0,$t0,$t0
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-48
lw $t0,0($t0)
lw $t1,0($sp)
addiu $sp,$sp,4
add $t0,$t0,$zero
add $t0,$t1,$t0
addiu $sp,$sp,-4
sw $t0,0($sp)
addiu $t0,$fp,-52
lw $t1,0($t0)
lw $t0,0($sp)
addiu $sp,$sp,4
add $t0,$t1,$zero
add $t0,$t0,$t0
addiu $t0,$fp,-56
sw $t0,0($t0)
addiu $t0,$fp,-56
lw $t0,0($t0)
add $v0,$t0,$zero
j label_5_return_helper
label_5_return_helper:
addiu $sp,$sp,56
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
jal helper
addiu $sp,$sp,0
add $t0,$v0,$zero
addiu $t0,$fp,-12
sw $t0,0($t0)
addiu $t0,$fp,-12
lw $t0,0($t0)
add $a0,$t0,$zero
li $v0,1
syscall
add $t0,$zero,$zero
label_7_return_main:
addiu $sp,$sp,12
lw $ra,0($sp)
lw $fp,4($sp)
addiu $sp,$sp,8
li $v0,10
syscall

