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
str_255904327:
.asciiz "Enter key:\n"
# Allocated labels for virtual registers
.align 2
label_51_v18:
.space 4
.align 2
label_101_v43:
.space 4
.align 2
label_62_v24:
.space 4
.align 2
label_106_v45:
.space 4
.align 2
label_132_v58:
.space 4
.align 2
label_122_v53:
.space 4
.align 2
label_118_v51:
.space 4
.align 2
label_47_v15:
.space 4
.align 2
label_36_v8:
.space 4
.align 2
label_76_v31:
.space 4
.align 2
label_79_v33:
.space 4
.align 2
label_52_v19:
.space 4
.align 2
label_86_v35:
.space 4
.align 2
label_58_v22:
.space 4
.align 2
label_64_v25:
.space 4
.align 2
label_43_v12:
.space 4
.align 2
label_92_v38:
.space 4
.align 2
label_29_v7:
.space 4
.align 2
label_130_v57:
.space 4
.align 2
label_84_v34:
.space 4
.align 2
label_34_v9:
.space 4
.align 2
label_30_v5:
.space 4
.align 2
label_75_v30:
.space 4
.align 2
label_116_v50:
.space 4
.align 2
label_98_v41:
.space 4
.align 2
label_115_v49:
.space 4
.align 2
label_27_v6:
.space 4
.align 2
label_17_v0:
.space 4
.align 2
label_125_v54:
.space 4
.align 2
label_18_v1:
.space 4
.align 2
label_72_v28:
.space 4
.align 2
label_61_v20:
.space 4
.align 2
label_55_v21:
.space 4
.align 2
label_93_v39:
.space 4
.align 2
label_44_v14:
.space 4
.align 2
label_41_v13:
.space 4
.align 2
label_24_v4:
.space 4
.align 2
label_67_v27:
.space 4
.align 2
label_22_v3:
.space 4
.align 2
label_59_v23:
.space 4
.align 2
label_121_v52:
.space 4
.align 2
label_95_v36:
.space 4
.align 2
label_82_v29:
.space 4
.align 2
label_33_v10:
.space 4
.align 2
label_21_v2:
.space 4
.align 2
label_111_v47:
.space 4
.align 2
label_80_v32:
.space 4
.align 2
label_96_v40:
.space 4
.align 2
label_103_v42:
.space 4
.align 2
label_126_v55:
.space 4
.align 2
label_129_v56:
.space 4
.align 2
label_108_v46:
.space 4
.align 2
label_112_v48:
.space 4
.align 2
label_89_v37:
.space 4
.align 2
label_38_v11:
.space 4
.align 2
label_73_v26:
.space 4
.align 2
label_48_v16:
.space 4
.align 2
label_104_v44:
.space 4
# Allocated labels for virtual registers
.align 2
label_217_v101:
.space 4
.align 2
label_191_v83:
.space 4
.align 2
label_206_v95:
.space 4
.align 2
label_207_v96:
.space 4
.align 2
label_201_v93:
.space 4
.align 2
label_153_v69:
.space 4
.align 2
label_198_v92:
.space 4
.align 2
label_141_v63:
.space 4
.align 2
label_162_v74:
.space 4
.align 2
label_144_v64:
.space 4
.align 2
label_149_v67:
.space 4
.align 2
label_152_v66:
.space 4
.align 2
label_176_v78:
.space 4
.align 2
label_225_v105:
.space 4
.align 2
label_164_v72:
.space 4
.align 2
label_221_v103:
.space 4
.align 2
label_174_v80:
.space 4
.align 2
label_137_v61:
.space 4
.align 2
label_197_v91:
.space 4
.align 2
label_186_v86:
.space 4
.align 2
label_210_v97:
.space 4
.align 2
label_168_v76:
.space 4
.align 2
label_167_v71:
.space 4
.align 2
label_165_v75:
.space 4
.align 2
label_188_v84:
.space 4
.align 2
label_223_v104:
.space 4
.align 2
label_177_v81:
.space 4
.align 2
label_140_v60:
.space 4
.align 2
label_200_v90:
.space 4
.align 2
label_179_v77:
.space 4
.align 2
label_211_v98:
.space 4
.align 2
label_215_v100:
.space 4
.align 2
label_155_v65:
.space 4
.align 2
label_213_v99:
.space 4
.align 2
label_143_v59:
.space 4
.align 2
label_156_v70:
.space 4
.align 2
label_185_v85:
.space 4
.align 2
label_203_v89:
.space 4
.align 2
label_220_v102:
.space 4
.align 2
label_180_v82:
.space 4
.align 2
label_138_v62:
.space 4
.align 2
label_226_v106:
.space 4
.align 2
label_173_v79:
.space 4
.align 2
label_192_v88:
.space 4
.align 2
label_161_v73:
.space 4
.align 2
label_150_v68:
.space 4
.align 2
label_204_v94:
.space 4
.align 2
label_189_v87:
.space 4

.text
binary_search:
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
# Original instruction: addiu $sp,$sp,-20
addiu $sp,$sp,-20
# Original instruction: li v0,0
li $t5,0
la $t0,label_17_v0
sw $t5,0($t0)
# Original instruction: addiu v1,$fp,-12
addiu $t5,$fp,-12
la $t0,label_18_v1
sw $t5,0($t0)
# Original instruction: sw v0,0(v1)
la $t5,label_17_v0
lw $t5,0($t5)
la $t4,label_18_v1
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v2,5
li $t5,5
la $t0,label_21_v2
sw $t5,0($t0)
# Original instruction: addiu v3,$fp,-16
addiu $t5,$fp,-16
la $t0,label_22_v3
sw $t5,0($t0)
# Original instruction: sw v2,0(v3)
la $t5,label_21_v2
lw $t5,0($t5)
la $t4,label_22_v3
lw $t4,0($t4)
sw $t5,0($t4)
label_6_while_start:
# Original instruction: addiu v4,$fp,-12
addiu $t5,$fp,-12
la $t0,label_24_v4
sw $t5,0($t0)
# Original instruction: lw v5,0(v4)
la $t5,label_24_v4
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_30_v5
sw $t4,0($t0)
# Original instruction: addiu v6,$fp,-16
addiu $t5,$fp,-16
la $t0,label_27_v6
sw $t5,0($t0)
# Original instruction: lw v7,0(v6)
la $t5,label_27_v6
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_29_v7
sw $t4,0($t0)
# Original instruction: slt v9,v7,v5
la $t5,label_29_v7
lw $t5,0($t5)
la $t4,label_30_v5
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_34_v9
sw $t3,0($t0)
# Original instruction: li v10,1
li $t5,1
la $t0,label_33_v10
sw $t5,0($t0)
# Original instruction: sub v8,v10,v9
la $t5,label_33_v10
lw $t5,0($t5)
la $t4,label_34_v9
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_36_v8
sw $t3,0($t0)
# Original instruction: beqz v8,label_7_while_exit
la $t5,label_36_v8
lw $t5,0($t5)
beqz $t5,label_7_while_exit
# Original instruction: addiu v11,$fp,-12
addiu $t5,$fp,-12
la $t0,label_38_v11
sw $t5,0($t0)
# Original instruction: lw v12,0(v11)
la $t5,label_38_v11
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_43_v12
sw $t4,0($t0)
# Original instruction: addiu v13,$fp,-16
addiu $t5,$fp,-16
la $t0,label_41_v13
sw $t5,0($t0)
# Original instruction: lw v14,0(v13)
la $t5,label_41_v13
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_44_v14
sw $t4,0($t0)
# Original instruction: add v15,v12,v14
la $t5,label_43_v12
lw $t5,0($t5)
la $t4,label_44_v14
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_47_v15
sw $t3,0($t0)
# Original instruction: li v16,2
li $t5,2
la $t0,label_48_v16
sw $t5,0($t0)
# Original instruction: div v15,v16
la $t5,label_47_v15
lw $t5,0($t5)
la $t4,label_48_v16
lw $t4,0($t4)
div $t5,$t4
# Original instruction: mflo v18
mflo $t5
la $t0,label_51_v18
sw $t5,0($t0)
# Original instruction: addiu v19,$fp,-20
addiu $t5,$fp,-20
la $t0,label_52_v19
sw $t5,0($t0)
# Original instruction: sw v18,0(v19)
la $t5,label_51_v18
lw $t5,0($t5)
la $t4,label_52_v19
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: lw v20,8($fp)
lw $t5,8($fp)
la $t0,label_61_v20
sw $t5,0($t0)
# Original instruction: addiu v21,$fp,-20
addiu $t5,$fp,-20
la $t0,label_55_v21
sw $t5,0($t0)
# Original instruction: lw v22,0(v21)
la $t5,label_55_v21
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_58_v22
sw $t4,0($t0)
# Original instruction: li v23,4
li $t5,4
la $t0,label_59_v23
sw $t5,0($t0)
# Original instruction: mul v24,v22,v23
la $t5,label_58_v22
lw $t5,0($t5)
la $t4,label_59_v23
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_62_v24
sw $t3,0($t0)
# Original instruction: add v25,v20,v24
la $t5,label_61_v20
lw $t5,0($t5)
la $t4,label_62_v24
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_64_v25
sw $t3,0($t0)
# Original instruction: lw v26,0(v25)
la $t5,label_64_v25
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_73_v26
sw $t4,0($t0)
# Original instruction: addiu v27,$fp,12
addiu $t5,$fp,12
la $t0,label_67_v27
sw $t5,0($t0)
# Original instruction: lw v28,0(v27)
la $t5,label_67_v27
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_72_v28
sw $t4,0($t0)
# Original instruction: slt v30,v26,v28
la $t5,label_73_v26
lw $t5,0($t5)
la $t4,label_72_v28
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_75_v30
sw $t3,0($t0)
# Original instruction: slt v31,v28,v26
la $t5,label_72_v28
lw $t5,0($t5)
la $t4,label_73_v26
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_76_v31
sw $t3,0($t0)
# Original instruction: or v32,v30,v31
la $t5,label_75_v30
lw $t5,0($t5)
la $t4,label_76_v31
lw $t4,0($t4)
or $t3,$t5,$t4
la $t0,label_80_v32
sw $t3,0($t0)
# Original instruction: li v33,1
li $t5,1
la $t0,label_79_v33
sw $t5,0($t0)
# Original instruction: sub v29,v33,v32
la $t5,label_79_v33
lw $t5,0($t5)
la $t4,label_80_v32
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_82_v29
sw $t3,0($t0)
# Original instruction: beqz v29,label_8_if_else
la $t5,label_82_v29
lw $t5,0($t5)
beqz $t5,label_8_if_else
# Original instruction: addiu v34,$fp,-20
addiu $t5,$fp,-20
la $t0,label_84_v34
sw $t5,0($t0)
# Original instruction: lw v35,0(v34)
la $t5,label_84_v34
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_86_v35
sw $t4,0($t0)
# Original instruction: add $v0,v35,$zero
la $t5,label_86_v35
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_binary_search
j label_5_return_binary_search
# Original instruction: j label_9_if_end
j label_9_if_end
label_8_if_else:
# Original instruction: lw v36,8($fp)
lw $t5,8($fp)
la $t0,label_95_v36
sw $t5,0($t0)
# Original instruction: addiu v37,$fp,-20
addiu $t5,$fp,-20
la $t0,label_89_v37
sw $t5,0($t0)
# Original instruction: lw v38,0(v37)
la $t5,label_89_v37
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_92_v38
sw $t4,0($t0)
# Original instruction: li v39,4
li $t5,4
la $t0,label_93_v39
sw $t5,0($t0)
# Original instruction: mul v40,v38,v39
la $t5,label_92_v38
lw $t5,0($t5)
la $t4,label_93_v39
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_96_v40
sw $t3,0($t0)
# Original instruction: add v41,v36,v40
la $t5,label_95_v36
lw $t5,0($t5)
la $t4,label_96_v40
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_98_v41
sw $t3,0($t0)
# Original instruction: lw v42,0(v41)
la $t5,label_98_v41
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_103_v42
sw $t4,0($t0)
# Original instruction: addiu v43,$fp,12
addiu $t5,$fp,12
la $t0,label_101_v43
sw $t5,0($t0)
# Original instruction: lw v44,0(v43)
la $t5,label_101_v43
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_104_v44
sw $t4,0($t0)
# Original instruction: slt v45,v42,v44
la $t5,label_103_v42
lw $t5,0($t5)
la $t4,label_104_v44
lw $t4,0($t4)
slt $t3,$t5,$t4
la $t0,label_106_v45
sw $t3,0($t0)
# Original instruction: beqz v45,label_10_if_else
la $t5,label_106_v45
lw $t5,0($t5)
beqz $t5,label_10_if_else
# Original instruction: addiu v46,$fp,-20
addiu $t5,$fp,-20
la $t0,label_108_v46
sw $t5,0($t0)
# Original instruction: lw v47,0(v46)
la $t5,label_108_v46
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_111_v47
sw $t4,0($t0)
# Original instruction: li v48,1
li $t5,1
la $t0,label_112_v48
sw $t5,0($t0)
# Original instruction: add v49,v47,v48
la $t5,label_111_v47
lw $t5,0($t5)
la $t4,label_112_v48
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_115_v49
sw $t3,0($t0)
# Original instruction: addiu v50,$fp,-12
addiu $t5,$fp,-12
la $t0,label_116_v50
sw $t5,0($t0)
# Original instruction: sw v49,0(v50)
la $t5,label_115_v49
lw $t5,0($t5)
la $t4,label_116_v50
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: j label_11_if_end
j label_11_if_end
label_10_if_else:
# Original instruction: addiu v51,$fp,-20
addiu $t5,$fp,-20
la $t0,label_118_v51
sw $t5,0($t0)
# Original instruction: lw v52,0(v51)
la $t5,label_118_v51
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_121_v52
sw $t4,0($t0)
# Original instruction: li v53,1
li $t5,1
la $t0,label_122_v53
sw $t5,0($t0)
# Original instruction: sub v54,v52,v53
la $t5,label_121_v52
lw $t5,0($t5)
la $t4,label_122_v53
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_125_v54
sw $t3,0($t0)
# Original instruction: addiu v55,$fp,-16
addiu $t5,$fp,-16
la $t0,label_126_v55
sw $t5,0($t0)
# Original instruction: sw v54,0(v55)
la $t5,label_125_v54
lw $t5,0($t5)
la $t4,label_126_v55
lw $t4,0($t4)
sw $t5,0($t4)
label_11_if_end:
label_9_if_end:
# Original instruction: j label_6_while_start
j label_6_while_start
label_7_while_exit:
# Original instruction: li v56,0
li $t5,0
la $t0,label_129_v56
sw $t5,0($t0)
# Original instruction: li v57,1
li $t5,1
la $t0,label_130_v57
sw $t5,0($t0)
# Original instruction: sub v58,v56,v57
la $t5,label_129_v56
lw $t5,0($t5)
la $t4,label_130_v57
lw $t4,0($t4)
sub $t3,$t5,$t4
la $t0,label_132_v58
sw $t3,0($t0)
# Original instruction: add $v0,v58,$zero
la $t5,label_132_v58
lw $t5,0($t5)
add $v0,$t5,$zero
# Original instruction: j label_5_return_binary_search
j label_5_return_binary_search
label_5_return_binary_search:
# Original instruction: addiu $sp,$sp,20
addiu $sp,$sp,20
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
# Original instruction: addiu $sp,$sp,-40
addiu $sp,$sp,-40
# Original instruction: li v59,2
li $t5,2
la $t0,label_143_v59
sw $t5,0($t0)
# Original instruction: addiu v60,$fp,-32
addiu $t5,$fp,-32
la $t0,label_140_v60
sw $t5,0($t0)
# Original instruction: li v61,0
li $t5,0
la $t0,label_137_v61
sw $t5,0($t0)
# Original instruction: li v62,4
li $t5,4
la $t0,label_138_v62
sw $t5,0($t0)
# Original instruction: mul v63,v61,v62
la $t5,label_137_v61
lw $t5,0($t5)
la $t4,label_138_v62
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_141_v63
sw $t3,0($t0)
# Original instruction: add v64,v60,v63
la $t5,label_140_v60
lw $t5,0($t5)
la $t4,label_141_v63
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_144_v64
sw $t3,0($t0)
# Original instruction: sw v59,0(v64)
la $t5,label_143_v59
lw $t5,0($t5)
la $t4,label_144_v64
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v65,4
li $t5,4
la $t0,label_155_v65
sw $t5,0($t0)
# Original instruction: addiu v66,$fp,-32
addiu $t5,$fp,-32
la $t0,label_152_v66
sw $t5,0($t0)
# Original instruction: li v67,1
li $t5,1
la $t0,label_149_v67
sw $t5,0($t0)
# Original instruction: li v68,4
li $t5,4
la $t0,label_150_v68
sw $t5,0($t0)
# Original instruction: mul v69,v67,v68
la $t5,label_149_v67
lw $t5,0($t5)
la $t4,label_150_v68
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_153_v69
sw $t3,0($t0)
# Original instruction: add v70,v66,v69
la $t5,label_152_v66
lw $t5,0($t5)
la $t4,label_153_v69
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_156_v70
sw $t3,0($t0)
# Original instruction: sw v65,0(v70)
la $t5,label_155_v65
lw $t5,0($t5)
la $t4,label_156_v70
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v71,7
li $t5,7
la $t0,label_167_v71
sw $t5,0($t0)
# Original instruction: addiu v72,$fp,-32
addiu $t5,$fp,-32
la $t0,label_164_v72
sw $t5,0($t0)
# Original instruction: li v73,2
li $t5,2
la $t0,label_161_v73
sw $t5,0($t0)
# Original instruction: li v74,4
li $t5,4
la $t0,label_162_v74
sw $t5,0($t0)
# Original instruction: mul v75,v73,v74
la $t5,label_161_v73
lw $t5,0($t5)
la $t4,label_162_v74
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_165_v75
sw $t3,0($t0)
# Original instruction: add v76,v72,v75
la $t5,label_164_v72
lw $t5,0($t5)
la $t4,label_165_v75
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_168_v76
sw $t3,0($t0)
# Original instruction: sw v71,0(v76)
la $t5,label_167_v71
lw $t5,0($t5)
la $t4,label_168_v76
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v77,9
li $t5,9
la $t0,label_179_v77
sw $t5,0($t0)
# Original instruction: addiu v78,$fp,-32
addiu $t5,$fp,-32
la $t0,label_176_v78
sw $t5,0($t0)
# Original instruction: li v79,3
li $t5,3
la $t0,label_173_v79
sw $t5,0($t0)
# Original instruction: li v80,4
li $t5,4
la $t0,label_174_v80
sw $t5,0($t0)
# Original instruction: mul v81,v79,v80
la $t5,label_173_v79
lw $t5,0($t5)
la $t4,label_174_v80
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_177_v81
sw $t3,0($t0)
# Original instruction: add v82,v78,v81
la $t5,label_176_v78
lw $t5,0($t5)
la $t4,label_177_v81
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_180_v82
sw $t3,0($t0)
# Original instruction: sw v77,0(v82)
la $t5,label_179_v77
lw $t5,0($t5)
la $t4,label_180_v82
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v83,15
li $t5,15
la $t0,label_191_v83
sw $t5,0($t0)
# Original instruction: addiu v84,$fp,-32
addiu $t5,$fp,-32
la $t0,label_188_v84
sw $t5,0($t0)
# Original instruction: li v85,4
li $t5,4
la $t0,label_185_v85
sw $t5,0($t0)
# Original instruction: li v86,4
li $t5,4
la $t0,label_186_v86
sw $t5,0($t0)
# Original instruction: mul v87,v85,v86
la $t5,label_185_v85
lw $t5,0($t5)
la $t4,label_186_v86
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_189_v87
sw $t3,0($t0)
# Original instruction: add v88,v84,v87
la $t5,label_188_v84
lw $t5,0($t5)
la $t4,label_189_v87
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_192_v88
sw $t3,0($t0)
# Original instruction: sw v83,0(v88)
la $t5,label_191_v83
lw $t5,0($t5)
la $t4,label_192_v88
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v89,99
li $t5,99
la $t0,label_203_v89
sw $t5,0($t0)
# Original instruction: addiu v90,$fp,-32
addiu $t5,$fp,-32
la $t0,label_200_v90
sw $t5,0($t0)
# Original instruction: li v91,5
li $t5,5
la $t0,label_197_v91
sw $t5,0($t0)
# Original instruction: li v92,4
li $t5,4
la $t0,label_198_v92
sw $t5,0($t0)
# Original instruction: mul v93,v91,v92
la $t5,label_197_v91
lw $t5,0($t5)
la $t4,label_198_v92
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_201_v93
sw $t3,0($t0)
# Original instruction: add v94,v90,v93
la $t5,label_200_v90
lw $t5,0($t5)
la $t4,label_201_v93
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_204_v94
sw $t3,0($t0)
# Original instruction: sw v89,0(v94)
la $t5,label_203_v89
lw $t5,0($t5)
la $t4,label_204_v94
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: la v95,str_255904327
la $t5,str_255904327
la $t0,label_206_v95
sw $t5,0($t0)
# Original instruction: add $a0,v95,$zero
la $t5,label_206_v95
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,4
li $v0,4
# Original instruction: syscall
syscall
# Original instruction: add v96,$zero,$zero
add $t5,$zero,$zero
la $t0,label_207_v96
sw $t5,0($t0)
# Original instruction: li $v0,5
li $v0,5
# Original instruction: syscall
syscall
# Original instruction: add v97,$v0,$zero
add $t5,$v0,$zero
la $t0,label_210_v97
sw $t5,0($t0)
# Original instruction: addiu v98,$fp,-40
addiu $t5,$fp,-40
la $t0,label_211_v98
sw $t5,0($t0)
# Original instruction: sw v97,0(v98)
la $t5,label_210_v97
lw $t5,0($t5)
la $t4,label_211_v98
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v99,$fp,-40
addiu $t5,$fp,-40
la $t0,label_213_v99
sw $t5,0($t0)
# Original instruction: lw v100,0(v99)
la $t5,label_213_v99
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_215_v100
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v100,0($sp)
la $t5,label_215_v100
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: addiu v101,$fp,-32
addiu $t5,$fp,-32
la $t0,label_217_v101
sw $t5,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v101,0($sp)
la $t5,label_217_v101
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal binary_search
jal binary_search
# Original instruction: addiu $sp,$sp,8
addiu $sp,$sp,8
# Original instruction: add v102,$v0,$zero
add $t5,$v0,$zero
la $t0,label_220_v102
sw $t5,0($t0)
# Original instruction: addiu v103,$fp,-36
addiu $t5,$fp,-36
la $t0,label_221_v103
sw $t5,0($t0)
# Original instruction: sw v102,0(v103)
la $t5,label_220_v102
lw $t5,0($t5)
la $t4,label_221_v103
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: addiu v104,$fp,-36
addiu $t5,$fp,-36
la $t0,label_223_v104
sw $t5,0($t0)
# Original instruction: lw v105,0(v104)
la $t5,label_223_v104
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_225_v105
sw $t4,0($t0)
# Original instruction: add $a0,v105,$zero
la $t5,label_225_v105
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v106,$zero,$zero
add $t5,$zero,$zero
la $t0,label_226_v106
sw $t5,0($t0)
label_13_return_main:
# Original instruction: addiu $sp,$sp,40
addiu $sp,$sp,40
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

