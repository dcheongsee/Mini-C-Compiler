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
label_11_v1:
.space 4
label_21_v6:
.space 4
label_13_v2:
.space 4
label_24_v8:
.space 4
label_30_v11:
.space 4
label_33_v12:
.space 4
label_34_v13:
.space 4
label_19_v5:
.space 4
label_36_v14:
.space 4
label_17_v4:
.space 4
label_10_v0:
.space 4
label_16_v3:
.space 4
label_26_v9:
.space 4
label_23_v7:
.space 4
label_29_v10:
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
label_6_while_start:
# Original instruction: addiu v2,$fp,-4
addiu $t5,$fp,-4
la $t0,label_13_v2
sw $t5,0($t0)
# Original instruction: lw v3,0(v2)
la $t5,label_13_v2
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_16_v3
sw $t4,0($t0)
# Original instruction: li v4,3
li $t5,3
la $t0,label_17_v4
sw $t5,0($t0)
# Original instruction: slt v5,v3,v4
la $t5,label_16_v3
lw $t5,0($t5)
la $t4,label_17_v4
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_19_v5
sw $t3,0($t0)
# Original instruction: beqz v5,label_7_while_exit
la $t5,label_19_v5
lw $t5,0($t5)
beqz $t5,label_7_while_exit
# Original instruction: addiu v6,$fp,-4
addiu $t5,$fp,-4
la $t0,label_21_v6
sw $t5,0($t0)
# Original instruction: lw v7,0(v6)
la $t5,label_21_v6
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_23_v7
sw $t4,0($t0)
# Original instruction: add $a0,v7,$zero
la $t5,label_23_v7
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v8,$zero,$zero
add $t5,$zero,$zero
la $t0,label_24_v8
sw $t5,0($t0)
# Original instruction: addiu v9,$fp,-4
addiu $t5,$fp,-4
la $t0,label_26_v9
sw $t5,0($t0)
# Original instruction: lw v10,0(v9)
la $t5,label_26_v9
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_29_v10
sw $t4,0($t0)
# Original instruction: li v11,1
li $t5,1
la $t0,label_30_v11
sw $t5,0($t0)
# Original instruction: add v12,v10,v11
la $t5,label_29_v10
lw $t5,0($t5)
la $t4,label_30_v11
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_33_v12
sw $t3,0($t0)
# Original instruction: addiu v13,$fp,-4
addiu $t5,$fp,-4
la $t0,label_34_v13
sw $t5,0($t0)
# Original instruction: sw v12,0(v13)
la $t5,label_33_v12
lw $t5,0($t5)
la $t4,label_34_v13
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_6_while_start
j label_6_while_start
label_7_while_exit:
# Original instruction: li v14,0
li $t5,0
la $t0,label_36_v14
sw $t5,0($t0)
# Original instruction: add $v0,v14,$zero
la $t5,label_36_v14
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

