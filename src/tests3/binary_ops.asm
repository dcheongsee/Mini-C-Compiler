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
label_21_v7:
.space 4
label_13_v3:
.space 4
label_23_v8:
.space 4
label_9_v1:
.space 4
label_16_v4:
.space 4
label_20_v6:
.space 4
label_26_v10:
.space 4
label_10_v2:
.space 4
label_17_v5:
.space 4
label_28_v11:
.space 4
label_25_v9:
.space 4
label_12_v0:
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
# Original instruction: li v0,2
li $t5,2
la $t0,label_12_v0
sw $t5,0($t0)
# Original instruction: li v1,3
li $t5,3
la $t0,label_9_v1
sw $t5,0($t0)
# Original instruction: li v2,4
li $t5,4
la $t0,label_10_v2
sw $t5,0($t0)
# Original instruction: mul v3,v1,v2
la $t5,label_9_v1
lw $t5,0($t5)
la $t4,label_10_v2
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_13_v3
sw $t3,0($t0)
# Original instruction: add v4,v0,v3
la $t5,label_12_v0
lw $t5,0($t5)
la $t4,label_13_v3
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_16_v4
sw $t3,0($t0)
# Original instruction: li v5,5
li $t5,5
la $t0,label_17_v5
sw $t5,0($t0)
# Original instruction: sub v6,v4,v5
la $t5,label_16_v4
lw $t5,0($t5)
la $t4,label_17_v5
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_20_v6
sw $t3,0($t0)
# Original instruction: addiu v7,$fp,-4
addiu $t5,$fp,-4
la $t0,label_21_v7
sw $t5,0($t0)
# Original instruction: sw v6,0(v7)
la $t5,label_20_v6
lw $t5,0($t5)
la $t4,label_21_v7
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v8,$fp,-4
addiu $t5,$fp,-4
la $t0,label_23_v8
sw $t5,0($t0)
# Original instruction: lw v9,0(v8)
la $t5,label_23_v8
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_25_v9
sw $t4,0($t0)
# Original instruction: add $a0,v9,$zero
la $t5,label_25_v9
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v10,$zero,$zero
add $t5,$zero,$zero
la $t0,label_26_v10
sw $t5,0($t0)
# Original instruction: li v11,0
li $t5,0
la $t0,label_28_v11
sw $t5,0($t0)
# Original instruction: add $v0,v11,$zero
la $t5,label_28_v11
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

