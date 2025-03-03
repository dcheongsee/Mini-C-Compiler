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
label_11_v2:
.space 4
label_9_v1:
.space 4
label_14_v4:
.space 4
label_13_v3:
.space 4
label_8_v0:
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
# Original instruction: li v0,42
li $t5,42
la $t0,label_8_v0
sw $t5,0($t0)
# Original instruction: addiu v1,$fp,-4
addiu $t5,$fp,-4
la $t0,label_9_v1
sw $t5,0($t0)
# Original instruction: sw v0,0(v1)
la $t5,label_8_v0
lw $t5,0($t5)
la $t4,label_9_v1
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v2,$fp,-4
addiu $t5,$fp,-4
la $t0,label_11_v2
sw $t5,0($t0)
# Original instruction: lw v3,0(v2)
la $t5,label_11_v2
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_13_v3
sw $t4,0($t0)
# Original instruction: add $a0,v3,$zero
la $t5,label_13_v3
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v4,$zero,$zero
add $t5,$zero,$zero
la $t0,label_14_v4
sw $t5,0($t0)
label_5_return_main:
# Original instruction: lw $ra,0($sp)
lw $ra,0($sp)
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: li $v0,10
li $v0,10
# Original instruction: syscall
syscall

