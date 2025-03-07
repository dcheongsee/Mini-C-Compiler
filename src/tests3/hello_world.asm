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
str_1848160949:
.asciiz "Hello, world!\n"
# Allocated labels for virtual registers
.align 2
label_8_v0:
.space 4
.align 2
label_9_v1:
.space 4

.text
.globl main
main:
# Original instruction: andi $sp,$sp,-4
andi $sp,$sp,-4
# Align sp to word boundary
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: sw $fp,4($sp)
sw $fp,4($sp)
# Original instruction: sw $ra,0($sp)
sw $ra,0($sp)
# Original instruction: add $fp,$sp,$zero
add $fp,$sp,$zero
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: la v0,str_1848160949
la $t5,str_1848160949
la $t0,label_8_v0
sw $t5,0($t0)
# Original instruction: add $a0,v0,$zero
la $t5,label_8_v0
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v1,$zero,$zero
add $t5,$zero,$zero
la $t0,label_9_v1
sw $t5,0($t0)
label_5_return_main:
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: lw $ra,0($sp)
lw $ra,0($sp)
# Original instruction: lw $fp,4($sp)
lw $fp,4($sp)
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: li $v0,10
li $v0,10
# Original instruction: syscall
syscall

