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
# Allocated labels for virtual registers
.align 2
label_36_v15:
.space 4
.align 2
label_52_v25:
.space 4
.align 2
label_55_v27:
.space 4
.align 2
label_18_v4:
.space 4
.align 2
label_22_v6:
.space 4
.align 2
label_15_v2:
.space 4
.align 2
label_43_v19:
.space 4
.align 2
label_32_v12:
.space 4
.align 2
label_28_v10:
.space 4
.align 2
label_49_v23:
.space 4
.align 2
label_39_v17:
.space 4
.align 2
label_41_v18:
.space 4
.align 2
label_50_v24:
.space 4
.align 2
label_25_v8:
.space 4
.align 2
label_26_v9:
.space 4
.align 2
label_46_v21:
.space 4
.align 2
label_11_v0:
.space 4
.align 2
label_12_v1:
.space 4
.align 2
label_31_v11:
.space 4
.align 2
label_38_v16:
.space 4
.align 2
label_21_v5:
.space 4
.align 2
label_44_v20:
.space 4
.align 2
label_47_v22:
.space 4
.align 2
label_54_v26:
.space 4
.align 2
label_35_v14:
.space 4
.align 2
label_16_v3:
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
# Original instruction: addiu $sp,$sp,-20
addiu $sp,$sp,-20
# Original instruction: la v0,str_2406971856
la $t5,str_2406971856
la $t0,label_11_v0
sw $t5,0($t0)
# Original instruction: add $a0,v0,$zero
la $t5,label_11_v0
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v1,$zero,$zero
add $t5,$zero,$zero
la $t0,label_12_v1
sw $t5,0($t0)
# Original instruction: li $v0,5
li $v0,5
# Original instruction: syscall
syscall
# Original instruction: add v2,$v0,$zero
add $t5,$v0,$zero
la $t0,label_15_v2
sw $t5,0($t0)
# Original instruction: addiu v3,$fp,-12
addiu $t5,$fp,-12
la $t0,label_16_v3
sw $t5,0($t0)
# Original instruction: sw v2,0(v3)
la $t5,label_15_v2
lw $t5,0($t5)
la $t4,label_16_v3
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v4,$fp,-12
addiu $t5,$fp,-12
la $t0,label_18_v4
sw $t5,0($t0)
# Original instruction: lw v5,0(v4)
la $t5,label_18_v4
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_21_v5
sw $t4,0($t0)
# Original instruction: li v6,10
li $t5,10
la $t0,label_22_v6
sw $t5,0($t0)
# Original instruction: div v5,v6
la $t5,label_21_v5
lw $t5,0($t5)
la $t4,label_22_v6
lw $t4,0($t4)
div $t5,$t4
# Original instruction: mflo v8
mflo $t5
la $t0,label_25_v8
sw $t5,0($t0)
# Original instruction: addiu v9,$fp,-16
addiu $t5,$fp,-16
la $t0,label_26_v9
sw $t5,0($t0)
# Original instruction: sw v8,0(v9)
la $t5,label_25_v8
lw $t5,0($t5)
la $t4,label_26_v9
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v10,$fp,-12
addiu $t5,$fp,-12
la $t0,label_28_v10
sw $t5,0($t0)
# Original instruction: lw v11,0(v10)
la $t5,label_28_v10
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_31_v11
sw $t4,0($t0)
# Original instruction: li v12,10
li $t5,10
la $t0,label_32_v12
sw $t5,0($t0)
# Original instruction: div v11,v12
la $t5,label_31_v11
lw $t5,0($t5)
la $t4,label_32_v12
lw $t4,0($t4)
div $t5,$t4
# Original instruction: mfhi v14
mfhi $t5
la $t0,label_35_v14
sw $t5,0($t0)
# Original instruction: addiu v15,$fp,-20
addiu $t5,$fp,-20
la $t0,label_36_v15
sw $t5,0($t0)
# Original instruction: sw v14,0(v15)
la $t5,label_35_v14
lw $t5,0($t5)
la $t4,label_36_v15
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: la v16,str_2501513638
la $t5,str_2501513638
la $t0,label_38_v16
sw $t5,0($t0)
# Original instruction: add $a0,v16,$zero
la $t5,label_38_v16
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v17,$zero,$zero
add $t5,$zero,$zero
la $t0,label_39_v17
sw $t5,0($t0)
# Original instruction: addiu v18,$fp,-16
addiu $t5,$fp,-16
la $t0,label_41_v18
sw $t5,0($t0)
# Original instruction: lw v19,0(v18)
la $t5,label_41_v18
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_43_v19
sw $t4,0($t0)
# Original instruction: add $a0,v19,$zero
la $t5,label_43_v19
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v20,$zero,$zero
add $t5,$zero,$zero
la $t0,label_44_v20
sw $t5,0($t0)
# Original instruction: la v21,str_10
la $t5,str_10
la $t0,label_46_v21
sw $t5,0($t0)
# Original instruction: add $a0,v21,$zero
la $t5,label_46_v21
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v22,$zero,$zero
add $t5,$zero,$zero
la $t0,label_47_v22
sw $t5,0($t0)
# Original instruction: la v23,str_2366411453
la $t5,str_2366411453
la $t0,label_49_v23
sw $t5,0($t0)
# Original instruction: add $a0,v23,$zero
la $t5,label_49_v23
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v24,$zero,$zero
add $t5,$zero,$zero
la $t0,label_50_v24
sw $t5,0($t0)
# Original instruction: addiu v25,$fp,-20
addiu $t5,$fp,-20
la $t0,label_52_v25
sw $t5,0($t0)
# Original instruction: lw v26,0(v25)
la $t5,label_52_v25
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_54_v26
sw $t4,0($t0)
# Original instruction: add $a0,v26,$zero
la $t5,label_54_v26
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v27,$zero,$zero
add $t5,$zero,$zero
la $t0,label_55_v27
sw $t5,0($t0)
label_5_return_main:
# Original instruction: addiu $sp,$sp,20
addiu $sp,$sp,20
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

