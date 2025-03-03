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
label_19_v5:
.space 4
label_11_v1:
.space 4
label_21_v6:
.space 4
label_13_v2:
.space 4
label_16_v4:
.space 4
label_24_v8:
.space 4
label_10_v0:
.space 4
label_17_v3:
.space 4
label_25_v9:
.space 4
label_22_v7:
.space 4
label_27_v10:
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
# Original instruction: li v0,5
li $t5,5
la $t0,label_10_v0
sw $t5,0($t0)
# Original instruction: addiu v1,$fp,-4
addiu $t5,$fp,-4
la $t0,label_11_v1
sw $t5,0($t0)
# Original instruction: sw v0,0(v1)
la $t5,label_10_v0
lw $t5,0($t5)
la $t4,label_11_v1
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v2,$fp,-4
addiu $t5,$fp,-4
la $t0,label_13_v2
sw $t5,0($t0)
# Original instruction: lw v3,0(v2)
la $t5,label_13_v2
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_17_v3
sw $t4,0($t0)
# Original instruction: li v4,3
li $t5,3
la $t0,label_16_v4
sw $t5,0($t0)
# Original instruction: slt v5,v4,v3
la $t5,label_16_v4
lw $t5,0($t5)
la $t4,label_17_v3
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_19_v5
sw $t3,0($t0)
# Original instruction: beqz v5,label_6_if_else
la $t5,label_19_v5
lw $t5,0($t5)
beqz $t5,label_6_if_else
# Original instruction: li v6,1
li $t5,1
la $t0,label_21_v6
sw $t5,0($t0)
# Original instruction: add $a0,v6,$zero
la $t5,label_21_v6
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v7,$zero,$zero
add $t5,$zero,$zero
la $t0,label_22_v7
sw $t5,0($t0)
# Original instruction: j label_7_if_end
j label_7_if_end
label_6_if_else:
# Original instruction: li v8,0
li $t5,0
la $t0,label_24_v8
sw $t5,0($t0)
# Original instruction: add $a0,v8,$zero
la $t5,label_24_v8
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v9,$zero,$zero
add $t5,$zero,$zero
la $t0,label_25_v9
sw $t5,0($t0)
label_7_if_end:
# Original instruction: li v10,0
li $t5,0
la $t0,label_27_v10
sw $t5,0($t0)
# Original instruction: add $v0,v10,$zero
la $t5,label_27_v10
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

