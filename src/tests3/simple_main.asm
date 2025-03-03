.data
global_c:
.align 2
.space 1
global_size:
.align 2
.space 4
global_s:
.align 2
.space 4
global_i:
.align 2
.space 4
# Allocated labels for virtual registers
label_7_v0:
.space 4

.text
.globl main
main:
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw $ra,0($sp)
sw $ra,0($sp)
# Original instruction: add $fp,$sp,$zero
add $fp,$sp,$zero
# Original instruction: li v0,0
li $t5,0
la $t0,label_7_v0
sw $t5,0($t0)
# Original instruction: add $v0,v0,$zero
la $t5,label_7_v0
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_main
j label_5_return_main
label_5_return_main:
# Original instruction: lw $ra,0($sp)
lw $ra,0($sp)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: li $v0,10
li $v0,10
# Original instruction: syscall
syscall

