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
str_3059791124:
.asciiz "Enter a year:\n"
str_3430832597:
.asciiz "Leap year"
str_1473121480:
.asciiz "Not leap year"
# Allocated labels for virtual registers
.align 2
label_71_v25:
.space 4
.align 2
label_38_v9:
.space 4
.align 2
label_92_v36:
.space 4
.align 2
label_86_v33:
.space 4
.align 2
label_79_v28:
.space 4
.align 2
label_78_v29:
.space 4
.align 2
label_64_v18:
.space 4
.align 2
label_61_v22:
.space 4
.align 2
label_42_v11:
.space 4
.align 2
label_90_v35:
.space 4
.align 2
label_33_v7:
.space 4
.align 2
label_40_v6:
.space 4
.align 2
label_66_v23:
.space 4
.align 2
label_82_v32:
.space 4
.align 2
label_57_v19:
.space 4
.align 2
label_72_v26:
.space 4
.align 2
label_54_v17:
.space 4
.align 2
label_23_v1:
.space 4
.align 2
label_37_v10:
.space 4
.align 2
label_81_v31:
.space 4
.align 2
label_85_v34:
.space 4
.align 2
label_62_v21:
.space 4
.align 2
label_48_v14:
.space 4
.align 2
label_31_v4:
.space 4
.align 2
label_20_v0:
.space 4
.align 2
label_30_v5:
.space 4
.align 2
label_55_v16:
.space 4
.align 2
label_44_v12:
.space 4
.align 2
label_24_v2:
.space 4
.align 2
label_58_v20:
.space 4
.align 2
label_88_v30:
.space 4
.align 2
label_68_v24:
.space 4
.align 2
label_47_v13:
.space 4
.align 2
label_34_v8:
.space 4
# Allocated labels for virtual registers
.align 2
label_124_v52:
.space 4
.align 2
label_117_v48:
.space 4
.align 2
label_94_v37:
.space 4
.align 2
label_122_v51:
.space 4
.align 2
label_119_v45:
.space 4
.align 2
label_109_v44:
.space 4
.align 2
label_113_v47:
.space 4
.align 2
label_116_v49:
.space 4
.align 2
label_98_v39:
.space 4
.align 2
label_125_v53:
.space 4
.align 2
label_103_v42:
.space 4
.align 2
label_110_v43:
.space 4
.align 2
label_121_v50:
.space 4
.align 2
label_112_v46:
.space 4
.align 2
label_99_v40:
.space 4
.align 2
label_101_v41:
.space 4
.align 2
label_95_v38:
.space 4

.text
is_leap:
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
# Original instruction: addiu v0,$fp,8
addiu $t5,$fp,8
la $t0,label_20_v0
sw $t5,0($t0)
# Original instruction: lw v1,0(v0)
la $t5,label_20_v0
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_23_v1
sw $t4,0($t0)
# Original instruction: li v2,400
li $t5,400
la $t0,label_24_v2
sw $t5,0($t0)
# Original instruction: div v1,v2
la $t5,label_23_v1
lw $t5,0($t5)
la $t4,label_24_v2
lw $t4,0($t4)
div $t5,$t4
# Original instruction: mfhi v4
mfhi $t5
la $t0,label_31_v4
sw $t5,0($t0)
# Original instruction: li v5,0
li $t5,0
la $t0,label_30_v5
sw $t5,0($t0)
# Original instruction: slt v7,v4,v5
la $t5,label_31_v4
lw $t5,0($t5)
la $t4,label_30_v5
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_33_v7
sw $t3,0($t0)
# Original instruction: slt v8,v5,v4
la $t5,label_30_v5
lw $t5,0($t5)
la $t4,label_31_v4
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_34_v8
sw $t3,0($t0)
# Original instruction: or v9,v7,v8
la $t5,label_33_v7
lw $t5,0($t5)
la $t4,label_34_v8
lw $t4,0($t4)
or $t3,$t5,$t4
la $t0,label_38_v9
sw $t3,0($t0)
# Original instruction: li v10,1
li $t5,1
la $t0,label_37_v10
sw $t5,0($t0)
# Original instruction: sub v6,v10,v9
la $t5,label_37_v10
lw $t5,0($t5)
la $t4,label_38_v9
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_40_v6
sw $t3,0($t0)
# Original instruction: beqz v6,label_6_if_else
la $t5,label_40_v6
lw $t5,0($t5)
beqz $t5,label_6_if_else
# Original instruction: li v11,1
li $t5,1
la $t0,label_42_v11
sw $t5,0($t0)
# Original instruction: add $v0,v11,$zero
la $t5,label_42_v11
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_is_leap
j label_5_return_is_leap
# Original instruction: j label_7_if_end
j label_7_if_end
label_6_if_else:
label_7_if_end:
# Original instruction: addiu v12,$fp,8
addiu $t5,$fp,8
la $t0,label_44_v12
sw $t5,0($t0)
# Original instruction: lw v13,0(v12)
la $t5,label_44_v12
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_47_v13
sw $t4,0($t0)
# Original instruction: li v14,100
li $t5,100
la $t0,label_48_v14
sw $t5,0($t0)
# Original instruction: div v13,v14
la $t5,label_47_v13
lw $t5,0($t5)
la $t4,label_48_v14
lw $t4,0($t4)
div $t5,$t4
# Original instruction: mfhi v16
mfhi $t5
la $t0,label_55_v16
sw $t5,0($t0)
# Original instruction: li v17,0
li $t5,0
la $t0,label_54_v17
sw $t5,0($t0)
# Original instruction: slt v19,v16,v17
la $t5,label_55_v16
lw $t5,0($t5)
la $t4,label_54_v17
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_57_v19
sw $t3,0($t0)
# Original instruction: slt v20,v17,v16
la $t5,label_54_v17
lw $t5,0($t5)
la $t4,label_55_v16
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_58_v20
sw $t3,0($t0)
# Original instruction: or v21,v19,v20
la $t5,label_57_v19
lw $t5,0($t5)
la $t4,label_58_v20
lw $t4,0($t4)
or $t3,$t5,$t4
la $t0,label_62_v21
sw $t3,0($t0)
# Original instruction: li v22,1
li $t5,1
la $t0,label_61_v22
sw $t5,0($t0)
# Original instruction: sub v18,v22,v21
la $t5,label_61_v22
lw $t5,0($t5)
la $t4,label_62_v21
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_64_v18
sw $t3,0($t0)
# Original instruction: beqz v18,label_8_if_else
la $t5,label_64_v18
lw $t5,0($t5)
beqz $t5,label_8_if_else
# Original instruction: li v23,0
li $t5,0
la $t0,label_66_v23
sw $t5,0($t0)
# Original instruction: add $v0,v23,$zero
la $t5,label_66_v23
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_is_leap
j label_5_return_is_leap
# Original instruction: j label_9_if_end
j label_9_if_end
label_8_if_else:
label_9_if_end:
# Original instruction: addiu v24,$fp,8
addiu $t5,$fp,8
la $t0,label_68_v24
sw $t5,0($t0)
# Original instruction: lw v25,0(v24)
la $t5,label_68_v24
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_71_v25
sw $t4,0($t0)
# Original instruction: li v26,4
li $t5,4
la $t0,label_72_v26
sw $t5,0($t0)
# Original instruction: div v25,v26
la $t5,label_71_v25
lw $t5,0($t5)
la $t4,label_72_v26
lw $t4,0($t4)
div $t5,$t4
# Original instruction: mfhi v28
mfhi $t5
la $t0,label_79_v28
sw $t5,0($t0)
# Original instruction: li v29,0
li $t5,0
la $t0,label_78_v29
sw $t5,0($t0)
# Original instruction: slt v31,v28,v29
la $t5,label_79_v28
lw $t5,0($t5)
la $t4,label_78_v29
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_81_v31
sw $t3,0($t0)
# Original instruction: slt v32,v29,v28
la $t5,label_78_v29
lw $t5,0($t5)
la $t4,label_79_v28
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_82_v32
sw $t3,0($t0)
# Original instruction: or v33,v31,v32
la $t5,label_81_v31
lw $t5,0($t5)
la $t4,label_82_v32
lw $t4,0($t4)
or $t3,$t5,$t4
la $t0,label_86_v33
sw $t3,0($t0)
# Original instruction: li v34,1
li $t5,1
la $t0,label_85_v34
sw $t5,0($t0)
# Original instruction: sub v30,v34,v33
la $t5,label_85_v34
lw $t5,0($t5)
la $t4,label_86_v33
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_88_v30
sw $t3,0($t0)
# Original instruction: beqz v30,label_10_if_else
la $t5,label_88_v30
lw $t5,0($t5)
beqz $t5,label_10_if_else
# Original instruction: li v35,1
li $t5,1
la $t0,label_90_v35
sw $t5,0($t0)
# Original instruction: add $v0,v35,$zero
la $t5,label_90_v35
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_is_leap
j label_5_return_is_leap
# Original instruction: j label_11_if_end
j label_11_if_end
label_10_if_else:
label_11_if_end:
# Original instruction: li v36,0
li $t5,0
la $t0,label_92_v36
sw $t5,0($t0)
# Original instruction: add $v0,v36,$zero
la $t5,label_92_v36
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_is_leap
j label_5_return_is_leap
label_5_return_is_leap:
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: lw $ra,0($sp)
lw $ra,0($sp)
# Original instruction: lw $fp,4($sp)
lw $fp,4($sp)
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: jr $ra
jr $ra

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
# Original instruction: addiu $sp,$sp,-12
addiu $sp,$sp,-12
# Original instruction: la v37,str_3059791124
la $t5,str_3059791124
la $t0,label_94_v37
sw $t5,0($t0)
# Original instruction: add $a0,v37,$zero
la $t5,label_94_v37
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v38,$zero,$zero
add $t5,$zero,$zero
la $t0,label_95_v38
sw $t5,0($t0)
# Original instruction: li $v0,5
li $v0,5
# Original instruction: syscall
syscall
# Original instruction: add v39,$v0,$zero
add $t5,$v0,$zero
la $t0,label_98_v39
sw $t5,0($t0)
# Original instruction: addiu v40,$fp,-12
addiu $t5,$fp,-12
la $t0,label_99_v40
sw $t5,0($t0)
# Original instruction: sw v39,0(v40)
la $t5,label_98_v39
lw $t5,0($t5)
la $t4,label_99_v40
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v41,$fp,-12
addiu $t5,$fp,-12
la $t0,label_101_v41
sw $t5,0($t0)
# Original instruction: lw v42,0(v41)
la $t5,label_101_v41
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_103_v42
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v42,0($sp)
la $t5,label_103_v42
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal is_leap
jal is_leap
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v43,$v0,$zero
add $t5,$v0,$zero
la $t0,label_110_v43
sw $t5,0($t0)
# Original instruction: li v44,1
li $t5,1
la $t0,label_109_v44
sw $t5,0($t0)
# Original instruction: slt v46,v43,v44
la $t5,label_110_v43
lw $t5,0($t5)
la $t4,label_109_v44
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_112_v46
sw $t3,0($t0)
# Original instruction: slt v47,v44,v43
la $t5,label_109_v44
lw $t5,0($t5)
la $t4,label_110_v43
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_113_v47
sw $t3,0($t0)
# Original instruction: or v48,v46,v47
la $t5,label_112_v46
lw $t5,0($t5)
la $t4,label_113_v47
lw $t4,0($t4)
or $t3,$t5,$t4
la $t0,label_117_v48
sw $t3,0($t0)
# Original instruction: li v49,1
li $t5,1
la $t0,label_116_v49
sw $t5,0($t0)
# Original instruction: sub v45,v49,v48
la $t5,label_116_v49
lw $t5,0($t5)
la $t4,label_117_v48
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_119_v45
sw $t3,0($t0)
# Original instruction: beqz v45,label_15_if_else
la $t5,label_119_v45
lw $t5,0($t5)
beqz $t5,label_15_if_else
# Original instruction: la v50,str_3430832597
la $t5,str_3430832597
la $t0,label_121_v50
sw $t5,0($t0)
# Original instruction: add $a0,v50,$zero
la $t5,label_121_v50
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v51,$zero,$zero
add $t5,$zero,$zero
la $t0,label_122_v51
sw $t5,0($t0)
# Original instruction: j label_16_if_end
j label_16_if_end
label_15_if_else:
# Original instruction: la v52,str_1473121480
la $t5,str_1473121480
la $t0,label_124_v52
sw $t5,0($t0)
# Original instruction: add $a0,v52,$zero
la $t5,label_124_v52
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v53,$zero,$zero
add $t5,$zero,$zero
la $t0,label_125_v53
sw $t5,0($t0)
label_16_if_end:
label_13_return_main:
# Original instruction: addiu $sp,$sp,12
addiu $sp,$sp,12
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

