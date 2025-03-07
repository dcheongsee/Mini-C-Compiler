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
# Allocated labels for virtual registers
.align 2
label_29_v4:
.space 4
.align 2
label_41_v10:
.space 4
.align 2
label_21_v0:
.space 4
.align 2
label_23_v1:
.space 4
.align 2
label_34_v7:
.space 4
.align 2
label_48_v11:
.space 4
.align 2
label_26_v2:
.space 4
.align 2
label_36_v8:
.space 4
.align 2
label_38_v6:
.space 4
.align 2
label_46_v13:
.space 4
.align 2
label_49_v14:
.space 4
.align 2
label_44_v12:
.space 4
.align 2
label_39_v9:
.space 4
.align 2
label_27_v3:
.space 4
.align 2
label_31_v5:
.space 4
# Allocated labels for virtual registers
.align 2
label_142_v58:
.space 4
.align 2
label_133_v53:
.space 4
.align 2
label_109_v41:
.space 4
.align 2
label_113_v44:
.space 4
.align 2
label_57_v17:
.space 4
.align 2
label_86_v32:
.space 4
.align 2
label_135_v54:
.space 4
.align 2
label_117_v45:
.space 4
.align 2
label_61_v20:
.space 4
.align 2
label_67_v18:
.space 4
.align 2
label_103_v39:
.space 4
.align 2
label_73_v25:
.space 4
.align 2
label_139_v56:
.space 4
.align 2
label_129_v47:
.space 4
.align 2
label_60_v19:
.space 4
.align 2
label_126_v50:
.space 4
.align 2
label_99_v37:
.space 4
.align 2
label_65_v21:
.space 4
.align 2
label_79_v27:
.space 4
.align 2
label_87_v31:
.space 4
.align 2
label_82_v29:
.space 4
.align 2
label_145_v59:
.space 4
.align 2
label_119_v46:
.space 4
.align 2
label_71_v24:
.space 4
.align 2
label_154_v64:
.space 4
.align 2
label_95_v35:
.space 4
.align 2
label_146_v60:
.space 4
.align 2
label_141_v57:
.space 4
.align 2
label_101_v38:
.space 4
.align 2
label_137_v55:
.space 4
.align 2
label_83_v30:
.space 4
.align 2
label_131_v52:
.space 4
.align 2
label_51_v15:
.space 4
.align 2
label_110_v40:
.space 4
.align 2
label_148_v61:
.space 4
.align 2
label_124_v49:
.space 4
.align 2
label_150_v62:
.space 4
.align 2
label_64_v22:
.space 4
.align 2
label_115_v42:
.space 4
.align 2
label_89_v28:
.space 4
.align 2
label_153_v63:
.space 4
.align 2
label_92_v33:
.space 4
.align 2
label_98_v36:
.space 4
.align 2
label_58_v16:
.space 4
.align 2
label_70_v23:
.space 4
.align 2
label_80_v26:
.space 4
.align 2
label_93_v34:
.space 4
.align 2
label_122_v48:
.space 4
.align 2
label_112_v43:
.space 4
.align 2
label_128_v51:
.space 4
# Allocated labels for virtual registers
.align 2
label_228_v101:
.space 4
.align 2
label_193_v83:
.space 4
.align 2
label_217_v95:
.space 4
.align 2
label_219_v96:
.space 4
.align 2
label_213_v91:
.space 4
.align 2
label_163_v69:
.space 4
.align 2
label_211_v93:
.space 4
.align 2
label_177_v74:
.space 4
.align 2
label_160_v67:
.space 4
.align 2
label_157_v66:
.space 4
.align 2
label_181_v78:
.space 4
.align 2
label_169_v72:
.space 4
.align 2
label_189_v80:
.space 4
.align 2
label_210_v92:
.space 4
.align 2
label_198_v86:
.space 4
.align 2
label_220_v97:
.space 4
.align 2
label_178_v76:
.space 4
.align 2
label_171_v71:
.space 4
.align 2
label_175_v75:
.space 4
.align 2
label_194_v84:
.space 4
.align 2
label_187_v81:
.space 4
.align 2
label_207_v90:
.space 4
.align 2
label_183_v77:
.space 4
.align 2
label_222_v98:
.space 4
.align 2
label_227_v100:
.space 4
.align 2
label_159_v65:
.space 4
.align 2
label_224_v99:
.space 4
.align 2
label_166_v70:
.space 4
.align 2
label_196_v85:
.space 4
.align 2
label_208_v89:
.space 4
.align 2
label_190_v82:
.space 4
.align 2
label_184_v79:
.space 4
.align 2
label_201_v88:
.space 4
.align 2
label_172_v73:
.space 4
.align 2
label_165_v68:
.space 4
.align 2
label_215_v94:
.space 4
.align 2
label_199_v87:
.space 4

.text
swap_nodes:
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
la $t0,label_21_v0
sw $t5,0($t0)
# Original instruction: lw v1,0(v0)
la $t5,label_21_v0
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_23_v1
sw $t4,0($t0)
# Original instruction: lw v2,0(v1)
la $t5,label_23_v1
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_26_v2
sw $t4,0($t0)
# Original instruction: addiu v3,$fp,-12
addiu $t5,$fp,-12
la $t0,label_27_v3
sw $t5,0($t0)
# Original instruction: sw v2,0(v3)
la $t5,label_26_v2
lw $t5,0($t5)
la $t4,label_27_v3
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v4,$fp,12
addiu $t5,$fp,12
la $t0,label_29_v4
sw $t5,0($t0)
# Original instruction: lw v5,0(v4)
la $t5,label_29_v4
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_31_v5
sw $t4,0($t0)
# Original instruction: lw v6,0(v5)
la $t5,label_31_v5
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_38_v6
sw $t4,0($t0)
# Original instruction: addiu v7,$fp,8
addiu $t5,$fp,8
la $t0,label_34_v7
sw $t5,0($t0)
# Original instruction: lw v8,0(v7)
la $t5,label_34_v7
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_36_v8
sw $t4,0($t0)
# Original instruction: addiu v9,v8,0
la $t5,label_36_v8
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_39_v9
sw $t4,0($t0)
# Original instruction: sw v6,0(v9)
la $t5,label_38_v6
lw $t5,0($t5)
la $t4,label_39_v9
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v10,$fp,-12
addiu $t5,$fp,-12
la $t0,label_41_v10
sw $t5,0($t0)
# Original instruction: lw v11,0(v10)
la $t5,label_41_v10
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_48_v11
sw $t4,0($t0)
# Original instruction: addiu v12,$fp,12
addiu $t5,$fp,12
la $t0,label_44_v12
sw $t5,0($t0)
# Original instruction: lw v13,0(v12)
la $t5,label_44_v12
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_46_v13
sw $t4,0($t0)
# Original instruction: addiu v14,v13,0
la $t5,label_46_v13
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_49_v14
sw $t4,0($t0)
# Original instruction: sw v11,0(v14)
la $t5,label_48_v11
lw $t5,0($t5)
la $t4,label_49_v14
lw $t4,0($t4)
sw $t5,0($t4)
label_5_return_swap_nodes:
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
bubble_sort:
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
# Original instruction: addiu $sp,$sp,-16
addiu $sp,$sp,-16
# Original instruction: addiu v15,$fp,8
addiu $t5,$fp,8
la $t0,label_51_v15
sw $t5,0($t0)
# Original instruction: lw v16,0(v15)
la $t5,label_51_v15
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_58_v16
sw $t4,0($t0)
# Original instruction: li v17,0
li $t5,0
la $t0,label_57_v17
sw $t5,0($t0)
# Original instruction: slt v19,v16,v17
la $t5,label_58_v16
lw $t5,0($t5)
la $t4,label_57_v17
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_60_v19
sw $t3,0($t0)
# Original instruction: slt v20,v17,v16
la $t5,label_57_v17
lw $t5,0($t5)
la $t4,label_58_v16
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_61_v20
sw $t3,0($t0)
# Original instruction: or v21,v19,v20
la $t5,label_60_v19
lw $t5,0($t5)
la $t4,label_61_v20
lw $t4,0($t4)
or $t3,$t5,$t4
la $t0,label_65_v21
sw $t3,0($t0)
# Original instruction: li v22,1
li $t5,1
la $t0,label_64_v22
sw $t5,0($t0)
# Original instruction: sub v18,v22,v21
la $t5,label_64_v22
lw $t5,0($t5)
la $t4,label_65_v21
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_67_v18
sw $t3,0($t0)
# Original instruction: beqz v18,label_8_if_else
la $t5,label_67_v18
lw $t5,0($t5)
beqz $t5,label_8_if_else
# Original instruction: j label_7_return_bubble_sort
j label_7_return_bubble_sort
# Original instruction: j label_9_if_end
j label_9_if_end
label_8_if_else:
label_9_if_end:
# Original instruction: li v23,1
li $t5,1
la $t0,label_70_v23
sw $t5,0($t0)
# Original instruction: addiu v24,$fp,-16
addiu $t5,$fp,-16
la $t0,label_71_v24
sw $t5,0($t0)
# Original instruction: sw v23,0(v24)
la $t5,label_70_v23
lw $t5,0($t5)
la $t4,label_71_v24
lw $t4,0($t4)
sw $t5,0($t4)
label_10_while_start:
# Original instruction: addiu v25,$fp,-16
addiu $t5,$fp,-16
la $t0,label_73_v25
sw $t5,0($t0)
# Original instruction: lw v26,0(v25)
la $t5,label_73_v25
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_80_v26
sw $t4,0($t0)
# Original instruction: li v27,1
li $t5,1
la $t0,label_79_v27
sw $t5,0($t0)
# Original instruction: slt v29,v26,v27
la $t5,label_80_v26
lw $t5,0($t5)
la $t4,label_79_v27
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_82_v29
sw $t3,0($t0)
# Original instruction: slt v30,v27,v26
la $t5,label_79_v27
lw $t5,0($t5)
la $t4,label_80_v26
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_83_v30
sw $t3,0($t0)
# Original instruction: or v31,v29,v30
la $t5,label_82_v29
lw $t5,0($t5)
la $t4,label_83_v30
lw $t4,0($t4)
or $t3,$t5,$t4
la $t0,label_87_v31
sw $t3,0($t0)
# Original instruction: li v32,1
li $t5,1
la $t0,label_86_v32
sw $t5,0($t0)
# Original instruction: sub v28,v32,v31
la $t5,label_86_v32
lw $t5,0($t5)
la $t4,label_87_v31
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_89_v28
sw $t3,0($t0)
# Original instruction: beqz v28,label_11_while_exit
la $t5,label_89_v28
lw $t5,0($t5)
beqz $t5,label_11_while_exit
# Original instruction: li v33,0
li $t5,0
la $t0,label_92_v33
sw $t5,0($t0)
# Original instruction: addiu v34,$fp,-16
addiu $t5,$fp,-16
la $t0,label_93_v34
sw $t5,0($t0)
# Original instruction: sw v33,0(v34)
la $t5,label_92_v33
lw $t5,0($t5)
la $t4,label_93_v34
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v35,$fp,8
addiu $t5,$fp,8
la $t0,label_95_v35
sw $t5,0($t0)
# Original instruction: lw v36,0(v35)
la $t5,label_95_v35
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_98_v36
sw $t4,0($t0)
# Original instruction: addiu v37,$fp,-12
addiu $t5,$fp,-12
la $t0,label_99_v37
sw $t5,0($t0)
# Original instruction: sw v36,0(v37)
la $t5,label_98_v36
lw $t5,0($t5)
la $t4,label_99_v37
lw $t4,0($t4)
sw $t5,0($t4)
label_12_while_start:
# Original instruction: addiu v38,$fp,-12
addiu $t5,$fp,-12
la $t0,label_101_v38
sw $t5,0($t0)
# Original instruction: lw v39,0(v38)
la $t5,label_101_v38
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_103_v39
sw $t4,0($t0)
# Original instruction: lw v40,4(v39)
la $t5,label_103_v39
lw $t5,0($t5)
lw $t4,4($t5)
la $t0,label_110_v40
sw $t4,0($t0)
# Original instruction: li v41,0
li $t5,0
la $t0,label_109_v41
sw $t5,0($t0)
# Original instruction: slt v43,v40,v41
la $t5,label_110_v40
lw $t5,0($t5)
la $t4,label_109_v41
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_112_v43
sw $t3,0($t0)
# Original instruction: slt v44,v41,v40
la $t5,label_109_v41
lw $t5,0($t5)
la $t4,label_110_v40
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_113_v44
sw $t3,0($t0)
# Original instruction: or v42,v43,v44
la $t5,label_112_v43
lw $t5,0($t5)
la $t4,label_113_v44
lw $t4,0($t4)
or $t3,$t5,$t4
la $t0,label_115_v42
sw $t3,0($t0)
# Original instruction: beqz v42,label_13_while_exit
la $t5,label_115_v42
lw $t5,0($t5)
beqz $t5,label_13_while_exit
# Original instruction: addiu v45,$fp,-12
addiu $t5,$fp,-12
la $t0,label_117_v45
sw $t5,0($t0)
# Original instruction: lw v46,0(v45)
la $t5,label_117_v45
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_119_v46
sw $t4,0($t0)
# Original instruction: lw v47,0(v46)
la $t5,label_119_v46
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_129_v47
sw $t4,0($t0)
# Original instruction: addiu v48,$fp,-12
addiu $t5,$fp,-12
la $t0,label_122_v48
sw $t5,0($t0)
# Original instruction: lw v49,0(v48)
la $t5,label_122_v48
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_124_v49
sw $t4,0($t0)
# Original instruction: lw v50,4(v49)
la $t5,label_124_v49
lw $t5,0($t5)
lw $t4,4($t5)
la $t0,label_126_v50
sw $t4,0($t0)
# Original instruction: lw v51,0(v50)
la $t5,label_126_v50
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_128_v51
sw $t4,0($t0)
# Original instruction: slt v52,v51,v47
la $t5,label_128_v51
lw $t5,0($t5)
la $t4,label_129_v47
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_131_v52
sw $t3,0($t0)
# Original instruction: beqz v52,label_14_if_else
la $t5,label_131_v52
lw $t5,0($t5)
beqz $t5,label_14_if_else
# Original instruction: addiu v53,$fp,-12
addiu $t5,$fp,-12
la $t0,label_133_v53
sw $t5,0($t0)
# Original instruction: lw v54,0(v53)
la $t5,label_133_v53
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_135_v54
sw $t4,0($t0)
# Original instruction: lw v55,4(v54)
la $t5,label_135_v54
lw $t5,0($t5)
lw $t4,4($t5)
la $t0,label_137_v55
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v55,0($sp)
la $t5,label_137_v55
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v56,$fp,-12
addiu $t5,$fp,-12
la $t0,label_139_v56
sw $t5,0($t0)
# Original instruction: lw v57,0(v56)
la $t5,label_139_v56
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_141_v57
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v57,0($sp)
la $t5,label_141_v57
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal swap_nodes
jal swap_nodes
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: add v58,$v0,$zero
add $t5,$v0,$zero
la $t0,label_142_v58
sw $t5,0($t0)
# Original instruction: li v59,1
li $t5,1
la $t0,label_145_v59
sw $t5,0($t0)
# Original instruction: addiu v60,$fp,-16
addiu $t5,$fp,-16
la $t0,label_146_v60
sw $t5,0($t0)
# Original instruction: sw v59,0(v60)
la $t5,label_145_v59
lw $t5,0($t5)
la $t4,label_146_v60
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_15_if_end
j label_15_if_end
label_14_if_else:
label_15_if_end:
# Original instruction: addiu v61,$fp,-12
addiu $t5,$fp,-12
la $t0,label_148_v61
sw $t5,0($t0)
# Original instruction: lw v62,0(v61)
la $t5,label_148_v61
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_150_v62
sw $t4,0($t0)
# Original instruction: lw v63,4(v62)
la $t5,label_150_v62
lw $t5,0($t5)
lw $t4,4($t5)
la $t0,label_153_v63
sw $t4,0($t0)
# Original instruction: addiu v64,$fp,-12
addiu $t5,$fp,-12
la $t0,label_154_v64
sw $t5,0($t0)
# Original instruction: sw v63,0(v64)
la $t5,label_153_v63
lw $t5,0($t5)
la $t4,label_154_v64
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_12_while_start
j label_12_while_start
label_13_while_exit:
# Original instruction: j label_10_while_start
j label_10_while_start
label_11_while_exit:
label_7_return_bubble_sort:
# Original instruction: addiu $sp,$sp,16
addiu $sp,$sp,16
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
# Original instruction: addiu $sp,$sp,-36
addiu $sp,$sp,-36
# Original instruction: li v65,30
li $t5,30
la $t0,label_159_v65
sw $t5,0($t0)
# Original instruction: addiu v66,$fp,-16
addiu $t5,$fp,-16
la $t0,label_157_v66
sw $t5,0($t0)
# Original instruction: addiu v67,v66,0
la $t5,label_157_v66
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_160_v67
sw $t4,0($t0)
# Original instruction: sw v65,0(v67)
la $t5,label_159_v65
lw $t5,0($t5)
la $t4,label_160_v67
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v68,10
li $t5,10
la $t0,label_165_v68
sw $t5,0($t0)
# Original instruction: addiu v69,$fp,-24
addiu $t5,$fp,-24
la $t0,label_163_v69
sw $t5,0($t0)
# Original instruction: addiu v70,v69,0
la $t5,label_163_v69
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_166_v70
sw $t4,0($t0)
# Original instruction: sw v68,0(v70)
la $t5,label_165_v68
lw $t5,0($t5)
la $t4,label_166_v70
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v71,20
li $t5,20
la $t0,label_171_v71
sw $t5,0($t0)
# Original instruction: addiu v72,$fp,-32
addiu $t5,$fp,-32
la $t0,label_169_v72
sw $t5,0($t0)
# Original instruction: addiu v73,v72,0
la $t5,label_169_v72
lw $t5,0($t5)
addiu $t4,$t5,0
la $t0,label_172_v73
sw $t4,0($t0)
# Original instruction: sw v71,0(v73)
la $t5,label_171_v71
lw $t5,0($t5)
la $t4,label_172_v73
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v74,$fp,-24
addiu $t5,$fp,-24
la $t0,label_177_v74
sw $t5,0($t0)
# Original instruction: addiu v75,$fp,-16
addiu $t5,$fp,-16
la $t0,label_175_v75
sw $t5,0($t0)
# Original instruction: addiu v76,v75,4
la $t5,label_175_v75
lw $t5,0($t5)
addiu $t4,$t5,4
la $t0,label_178_v76
sw $t4,0($t0)
# Original instruction: sw v74,0(v76)
la $t5,label_177_v74
lw $t5,0($t5)
la $t4,label_178_v76
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v77,$fp,-32
addiu $t5,$fp,-32
la $t0,label_183_v77
sw $t5,0($t0)
# Original instruction: addiu v78,$fp,-24
addiu $t5,$fp,-24
la $t0,label_181_v78
sw $t5,0($t0)
# Original instruction: addiu v79,v78,4
la $t5,label_181_v78
lw $t5,0($t5)
addiu $t4,$t5,4
la $t0,label_184_v79
sw $t4,0($t0)
# Original instruction: sw v77,0(v79)
la $t5,label_183_v77
lw $t5,0($t5)
la $t4,label_184_v79
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v80,0
li $t5,0
la $t0,label_189_v80
sw $t5,0($t0)
# Original instruction: addiu v81,$fp,-32
addiu $t5,$fp,-32
la $t0,label_187_v81
sw $t5,0($t0)
# Original instruction: addiu v82,v81,4
la $t5,label_187_v81
lw $t5,0($t5)
addiu $t4,$t5,4
la $t0,label_190_v82
sw $t4,0($t0)
# Original instruction: sw v80,0(v82)
la $t5,label_189_v80
lw $t5,0($t5)
la $t4,label_190_v82
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v83,$fp,-16
addiu $t5,$fp,-16
la $t0,label_193_v83
sw $t5,0($t0)
# Original instruction: addiu v84,$fp,-36
addiu $t5,$fp,-36
la $t0,label_194_v84
sw $t5,0($t0)
# Original instruction: sw v83,0(v84)
la $t5,label_193_v83
lw $t5,0($t5)
la $t4,label_194_v84
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v85,$fp,-36
addiu $t5,$fp,-36
la $t0,label_196_v85
sw $t5,0($t0)
# Original instruction: lw v86,0(v85)
la $t5,label_196_v85
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_198_v86
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v86,0($sp)
la $t5,label_198_v86
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal bubble_sort
jal bubble_sort
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v87,$v0,$zero
add $t5,$v0,$zero
la $t0,label_199_v87
sw $t5,0($t0)
label_18_while_start:
# Original instruction: addiu v88,$fp,-36
addiu $t5,$fp,-36
la $t0,label_201_v88
sw $t5,0($t0)
# Original instruction: lw v89,0(v88)
la $t5,label_201_v88
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_208_v89
sw $t4,0($t0)
# Original instruction: li v90,0
li $t5,0
la $t0,label_207_v90
sw $t5,0($t0)
# Original instruction: slt v92,v89,v90
la $t5,label_208_v89
lw $t5,0($t5)
la $t4,label_207_v90
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_210_v92
sw $t3,0($t0)
# Original instruction: slt v93,v90,v89
la $t5,label_207_v90
lw $t5,0($t5)
la $t4,label_208_v89
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_211_v93
sw $t3,0($t0)
# Original instruction: or v91,v92,v93
la $t5,label_210_v92
lw $t5,0($t5)
la $t4,label_211_v93
lw $t4,0($t4)
or $t3,$t5,$t4
la $t0,label_213_v91
sw $t3,0($t0)
# Original instruction: beqz v91,label_19_while_exit
la $t5,label_213_v91
lw $t5,0($t5)
beqz $t5,label_19_while_exit
# Original instruction: addiu v94,$fp,-36
addiu $t5,$fp,-36
la $t0,label_215_v94
sw $t5,0($t0)
# Original instruction: lw v95,0(v94)
la $t5,label_215_v94
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_217_v95
sw $t4,0($t0)
# Original instruction: lw v96,0(v95)
la $t5,label_217_v95
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_219_v96
sw $t4,0($t0)
# Original instruction: add $a0,v96,$zero
la $t5,label_219_v96
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v97,$zero,$zero
add $t5,$zero,$zero
la $t0,label_220_v97
sw $t5,0($t0)
# Original instruction: addiu v98,$fp,-36
addiu $t5,$fp,-36
la $t0,label_222_v98
sw $t5,0($t0)
# Original instruction: lw v99,0(v98)
la $t5,label_222_v98
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_224_v99
sw $t4,0($t0)
# Original instruction: lw v100,4(v99)
la $t5,label_224_v99
lw $t5,0($t5)
lw $t4,4($t5)
la $t0,label_227_v100
sw $t4,0($t0)
# Original instruction: addiu v101,$fp,-36
addiu $t5,$fp,-36
la $t0,label_228_v101
sw $t5,0($t0)
# Original instruction: sw v100,0(v101)
la $t5,label_227_v100
lw $t5,0($t5)
la $t4,label_228_v101
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_18_while_start
j label_18_while_start
label_19_while_exit:
label_17_return_main:
# Original instruction: addiu $sp,$sp,36
addiu $sp,$sp,36
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

