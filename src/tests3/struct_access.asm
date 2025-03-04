.data
global_x:
.align 2
.space 4
global_y:
.align 2
.space 1
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
label_12_v0:
.space 4
label_13_v2:
.space 4
label_33_v13:
.space 4
label_35_v14:
.space 4
label_19_v5:
.space 4
label_28_v10:
.space 4
label_21_v6:
.space 4
label_23_v7:
.space 4
label_32_v12:
.space 4
label_25_v8:
.space 4
label_30_v11:
.space 4
label_18_v3:
.space 4
label_10_v1:
.space 4
label_16_v4:
.space 4
label_26_v9:
.space 4

.text
.globl main
main:
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: sw $fp,4($sp)
sw $fp,4($sp)
# Original instruction: sw $ra,0($sp)
sw $ra,0($sp)
# Original instruction: add $fp,$sp,$zero
add $fp,$sp,$zero
# Original instruction: addiu $sp,$sp,-16
addiu $sp,$sp,-16
# Original instruction: li v0,100
li $t5,100
la $t0,label_12_v0
sw $t5,0($t0)
# Original instruction: addiu v1,$fp,-16
addiu $t5,$fp,-16
la $t0,label_10_v1
sw $t5,0($t0)
# Original instruction: addiu v2,v1,0
la $t5,label_10_v1
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_13_v2
sw $t4,0($t0)
# Original instruction: sw v0,0(v2)
la $t5,label_12_v0
lw $t5,0($t5)
la $t4,label_13_v2
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v3,65
li $t5,65
la $t0,label_18_v3
sw $t5,0($t0)
# Original instruction: addiu v4,$fp,-16
addiu $t5,$fp,-16
la $t0,label_16_v4
sw $t5,0($t0)
# Original instruction: addiu v5,v4,4
la $t5,label_16_v4
lw $t5,0($t5)
addiu $t4,$t5,4
la $t0,label_19_v5
sw $t4,0($t0)
# Original instruction: sb v3,0(v5)
la $t5,label_18_v3
lw $t5,0($t5)
la $t4,label_19_v5
lw $t4,0($t4)
sb $t5,0($t4)
# Original instruction: addiu v6,$fp,-16
addiu $t5,$fp,-16
la $t0,label_21_v6
sw $t5,0($t0)
# Original instruction: addiu v7,v6,0
la $t5,label_21_v6
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_23_v7
sw $t4,0($t0)
# Original instruction: lw v8,0(v7)
la $t5,label_23_v7
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_25_v8
sw $t4,0($t0)
# Original instruction: add $a0,v8,$zero
la $t5,label_25_v8
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v9,$zero,$zero
add $t5,$zero,$zero
la $t0,label_26_v9
sw $t5,0($t0)
# Original instruction: addiu v10,$fp,-16
addiu $t5,$fp,-16
la $t0,label_28_v10
sw $t5,0($t0)
# Original instruction: addiu v11,v10,4
la $t5,label_28_v10
lw $t5,0($t5)
addiu $t4,$t5,4
la $t0,label_30_v11
sw $t4,0($t0)
# Original instruction: lb v12,0(v11)
la $t5,label_30_v11
lw $t5,0($t5)
lb $t4,0($t5)
la $t0,label_32_v12
sw $t4,0($t0)
# Original instruction: add $a0,v12,$zero
la $t5,label_32_v12
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,11
li $v0,11
# Original instruction: syscall
syscall
# Original instruction: add v13,$zero,$zero
add $t5,$zero,$zero
la $t0,label_33_v13
sw $t5,0($t0)
# Original instruction: li v14,0
li $t5,0
la $t0,label_35_v14
sw $t5,0($t0)
# Original instruction: add $v0,v14,$zero
la $t5,label_35_v14
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_7_return_main
j label_7_return_main
label_7_return_main:
# Original instruction: addiu $sp,$sp,16
addiu $sp,$sp,16
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

