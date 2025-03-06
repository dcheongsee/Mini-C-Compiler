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
str_2023689345:
.asciiz "Enter number:\n"
str_2398849419:
.asciiz "Prime\n"
str_745730936:
.asciiz "Not prime\n"
# Allocated labels for virtual registers
.align 2
label_66_v23:
.space 4
.align 2
label_32_v6:
.space 4
.align 2
label_87_v33:
.space 4
.align 2
label_78_v29:
.space 4
.align 2
label_70_v26:
.space 4
.align 2
label_74_v27:
.space 4
.align 2
label_57_v19:
.space 4
.align 2
label_59_v18:
.space 4
.align 2
label_37_v9:
.space 4
.align 2
label_84_v32:
.space 4
.align 2
label_28_v4:
.space 4
.align 2
label_39_v8:
.space 4
.align 2
label_60_v20:
.space 4
.align 2
label_76_v24:
.space 4
.align 2
label_49_v16:
.space 4
.align 2
label_90_v35:
.space 4
.align 2
label_69_v25:
.space 4
.align 2
label_50_v15:
.space 4
.align 2
label_20_v0:
.space 4
.align 2
label_34_v7:
.space 4
.align 2
label_73_v28:
.space 4
.align 2
label_80_v30:
.space 4
.align 2
label_54_v17:
.space 4
.align 2
label_43_v12:
.space 4
.align 2
label_24_v2:
.space 4
.align 2
label_26_v3:
.space 4
.align 2
label_45_v13:
.space 4
.align 2
label_40_v10:
.space 4
.align 2
label_88_v34:
.space 4
.align 2
label_23_v1:
.space 4
.align 2
label_52_v14:
.space 4
.align 2
label_83_v31:
.space 4
.align 2
label_67_v22:
.space 4
.align 2
label_46_v11:
.space 4
.align 2
label_31_v5:
.space 4
# Allocated labels for virtual registers
.align 2
label_119_v49:
.space 4
.align 2
label_110_v45:
.space 4
.align 2
label_122_v51:
.space 4
.align 2
label_117_v44:
.space 4
.align 2
label_115_v47:
.space 4
.align 2
label_101_v41:
.space 4
.align 2
label_107_v43:
.space 4
.align 2
label_111_v46:
.space 4
.align 2
label_92_v36:
.space 4
.align 2
label_120_v50:
.space 4
.align 2
label_123_v52:
.space 4
.align 2
label_97_v39:
.space 4
.align 2
label_99_v40:
.space 4
.align 2
label_114_v48:
.space 4
.align 2
label_108_v42:
.space 4
.align 2
label_93_v37:
.space 4
.align 2
label_96_v38:
.space 4

.text
is_prime:
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
# Original instruction: li v2,2
li $t5,2
la $t0,label_24_v2
sw $t5,0($t0)
# Original instruction: slt v3,v1,v2
la $t5,label_23_v1
lw $t5,0($t5)
la $t4,label_24_v2
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_26_v3
sw $t3,0($t0)
# Original instruction: beqz v3,label_6_if_else
la $t5,label_26_v3
lw $t5,0($t5)
beqz $t5,label_6_if_else
# Original instruction: li v4,0
li $t5,0
la $t0,label_28_v4
sw $t5,0($t0)
# Original instruction: add $v0,v4,$zero
la $t5,label_28_v4
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_is_prime
j label_5_return_is_prime
# Original instruction: j label_7_if_end
j label_7_if_end
label_6_if_else:
label_7_if_end:
# Original instruction: li v5,2
li $t5,2
la $t0,label_31_v5
sw $t5,0($t0)
# Original instruction: addiu v6,$fp,-12
addiu $t5,$fp,-12
la $t0,label_32_v6
sw $t5,0($t0)
# Original instruction: sw v5,0(v6)
la $t5,label_31_v5
lw $t5,0($t5)
la $t4,label_32_v6
lw $t4,0($t4)
sw $t5,0($t4)
label_8_while_start:
# Original instruction: addiu v7,$fp,-12
addiu $t5,$fp,-12
la $t0,label_34_v7
sw $t5,0($t0)
# Original instruction: lw v8,0(v7)
la $t5,label_34_v7
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_39_v8
sw $t4,0($t0)
# Original instruction: addiu v9,$fp,-12
addiu $t5,$fp,-12
la $t0,label_37_v9
sw $t5,0($t0)
# Original instruction: lw v10,0(v9)
la $t5,label_37_v9
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_40_v10
sw $t4,0($t0)
# Original instruction: mul v11,v8,v10
la $t5,label_39_v8
lw $t5,0($t5)
la $t4,label_40_v10
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_46_v11
sw $t3,0($t0)
# Original instruction: addiu v12,$fp,8
addiu $t5,$fp,8
la $t0,label_43_v12
sw $t5,0($t0)
# Original instruction: lw v13,0(v12)
la $t5,label_43_v12
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_45_v13
sw $t4,0($t0)
# Original instruction: slt v15,v13,v11
la $t5,label_45_v13
lw $t5,0($t5)
la $t4,label_46_v11
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_50_v15
sw $t3,0($t0)
# Original instruction: li v16,1
li $t5,1
la $t0,label_49_v16
sw $t5,0($t0)
# Original instruction: sub v14,v16,v15
la $t5,label_49_v16
lw $t5,0($t5)
la $t4,label_50_v15
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_52_v14
sw $t3,0($t0)
# Original instruction: beqz v14,label_9_while_exit
la $t5,label_52_v14
lw $t5,0($t5)
beqz $t5,label_9_while_exit
# Original instruction: addiu v17,$fp,8
addiu $t5,$fp,8
la $t0,label_54_v17
sw $t5,0($t0)
# Original instruction: lw v18,0(v17)
la $t5,label_54_v17
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_59_v18
sw $t4,0($t0)
# Original instruction: addiu v19,$fp,-12
addiu $t5,$fp,-12
la $t0,label_57_v19
sw $t5,0($t0)
# Original instruction: lw v20,0(v19)
la $t5,label_57_v19
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_60_v20
sw $t4,0($t0)
# Original instruction: div v18,v20
la $t5,label_59_v18
lw $t5,0($t5)
la $t4,label_60_v20
lw $t4,0($t4)
div $t5,$t4
# Original instruction: mfhi v22
mfhi $t5
la $t0,label_67_v22
sw $t5,0($t0)
# Original instruction: li v23,0
li $t5,0
la $t0,label_66_v23
sw $t5,0($t0)
# Original instruction: slt v25,v22,v23
la $t5,label_67_v22
lw $t5,0($t5)
la $t4,label_66_v23
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_69_v25
sw $t3,0($t0)
# Original instruction: slt v26,v23,v22
la $t5,label_66_v23
lw $t5,0($t5)
la $t4,label_67_v22
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_70_v26
sw $t3,0($t0)
# Original instruction: or v27,v25,v26
la $t5,label_69_v25
lw $t5,0($t5)
la $t4,label_70_v26
lw $t4,0($t4)
or $t3,$t5,$t4
la $t0,label_74_v27
sw $t3,0($t0)
# Original instruction: li v28,1
li $t5,1
la $t0,label_73_v28
sw $t5,0($t0)
# Original instruction: sub v24,v28,v27
la $t5,label_73_v28
lw $t5,0($t5)
la $t4,label_74_v27
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_76_v24
sw $t3,0($t0)
# Original instruction: beqz v24,label_10_if_else
la $t5,label_76_v24
lw $t5,0($t5)
beqz $t5,label_10_if_else
# Original instruction: li v29,0
li $t5,0
la $t0,label_78_v29
sw $t5,0($t0)
# Original instruction: add $v0,v29,$zero
la $t5,label_78_v29
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_is_prime
j label_5_return_is_prime
# Original instruction: j label_11_if_end
j label_11_if_end
label_10_if_else:
label_11_if_end:
# Original instruction: addiu v30,$fp,-12
addiu $t5,$fp,-12
la $t0,label_80_v30
sw $t5,0($t0)
# Original instruction: lw v31,0(v30)
la $t5,label_80_v30
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_83_v31
sw $t4,0($t0)
# Original instruction: li v32,1
li $t5,1
la $t0,label_84_v32
sw $t5,0($t0)
# Original instruction: add v33,v31,v32
la $t5,label_83_v31
lw $t5,0($t5)
la $t4,label_84_v32
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_87_v33
sw $t3,0($t0)
# Original instruction: addiu v34,$fp,-12
addiu $t5,$fp,-12
la $t0,label_88_v34
sw $t5,0($t0)
# Original instruction: sw v33,0(v34)
la $t5,label_87_v33
lw $t5,0($t5)
la $t4,label_88_v34
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_8_while_start
j label_8_while_start
label_9_while_exit:
# Original instruction: li v35,1
li $t5,1
la $t0,label_90_v35
sw $t5,0($t0)
# Original instruction: add $v0,v35,$zero
la $t5,label_90_v35
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_is_prime
j label_5_return_is_prime
label_5_return_is_prime:
# Original instruction: addiu $sp,$sp,12
addiu $sp,$sp,12
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
# Original instruction: la v36,str_2023689345
la $t5,str_2023689345
la $t0,label_92_v36
sw $t5,0($t0)
# Original instruction: add $a0,v36,$zero
la $t5,label_92_v36
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v37,$zero,$zero
add $t5,$zero,$zero
la $t0,label_93_v37
sw $t5,0($t0)
# Original instruction: li $v0,5
li $v0,5
# Original instruction: syscall
syscall
# Original instruction: add v38,$v0,$zero
add $t5,$v0,$zero
la $t0,label_96_v38
sw $t5,0($t0)
# Original instruction: addiu v39,$fp,-12
addiu $t5,$fp,-12
la $t0,label_97_v39
sw $t5,0($t0)
# Original instruction: sw v38,0(v39)
la $t5,label_96_v38
lw $t5,0($t5)
la $t4,label_97_v39
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v40,$fp,-12
addiu $t5,$fp,-12
la $t0,label_99_v40
sw $t5,0($t0)
# Original instruction: lw v41,0(v40)
la $t5,label_99_v40
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_101_v41
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v41,0($sp)
la $t5,label_101_v41
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal is_prime
jal is_prime
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v42,$v0,$zero
add $t5,$v0,$zero
la $t0,label_108_v42
sw $t5,0($t0)
# Original instruction: li v43,1
li $t5,1
la $t0,label_107_v43
sw $t5,0($t0)
# Original instruction: slt v45,v42,v43
la $t5,label_108_v42
lw $t5,0($t5)
la $t4,label_107_v43
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_110_v45
sw $t3,0($t0)
# Original instruction: slt v46,v43,v42
la $t5,label_107_v43
lw $t5,0($t5)
la $t4,label_108_v42
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_111_v46
sw $t3,0($t0)
# Original instruction: or v47,v45,v46
la $t5,label_110_v45
lw $t5,0($t5)
la $t4,label_111_v46
lw $t4,0($t4)
or $t3,$t5,$t4
la $t0,label_115_v47
sw $t3,0($t0)
# Original instruction: li v48,1
li $t5,1
la $t0,label_114_v48
sw $t5,0($t0)
# Original instruction: sub v44,v48,v47
la $t5,label_114_v48
lw $t5,0($t5)
la $t4,label_115_v47
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_117_v44
sw $t3,0($t0)
# Original instruction: beqz v44,label_15_if_else
la $t5,label_117_v44
lw $t5,0($t5)
beqz $t5,label_15_if_else
# Original instruction: la v49,str_2398849419
la $t5,str_2398849419
la $t0,label_119_v49
sw $t5,0($t0)
# Original instruction: add $a0,v49,$zero
la $t5,label_119_v49
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v50,$zero,$zero
add $t5,$zero,$zero
la $t0,label_120_v50
sw $t5,0($t0)
# Original instruction: j label_16_if_end
j label_16_if_end
label_15_if_else:
# Original instruction: la v51,str_745730936
la $t5,str_745730936
la $t0,label_122_v51
sw $t5,0($t0)
# Original instruction: add $a0,v51,$zero
la $t5,label_122_v51
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v52,$zero,$zero
add $t5,$zero,$zero
la $t0,label_123_v52
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

