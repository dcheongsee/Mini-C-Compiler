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
label_116_v54:
.space 4
label_24_v8:
.space 4
label_113_v53:
.space 4
label_35_v11:
.space 4
label_23_v5:
.space 4
label_36_v14:
.space 4
label_87_v39:
.space 4
label_115_v51:
.space 4
label_13_v3:
.space 4
label_73_v33:
.space 4
label_76_v34:
.space 4
label_103_v45:
.space 4
label_12_v2:
.space 4
label_92_v42:
.space 4
label_41_v17:
.space 4
label_86_v30:
.space 4
label_72_v32:
.space 4
label_27_v9:
.space 4
label_80_v36:
.space 4
label_107_v49:
.space 4
label_56_v24:
.space 4
label_43_v15:
.space 4
label_126_v50:
.space 4
label_63_v25:
.space 4
label_120_v56:
.space 4
label_15_v1:
.space 4
label_83_v35:
.space 4
label_84_v38:
.space 4
label_127_v59:
.space 4
label_81_v37:
.space 4
label_20_v6:
.space 4
label_121_v57:
.space 4
label_40_v16:
.space 4
label_44_v18:
.space 4
label_124_v58:
.space 4
label_67_v29:
.space 4
label_53_v23:
.space 4
label_95_v41:
.space 4
label_55_v21:
.space 4
label_16_v4:
.space 4
label_96_v44:
.space 4
label_46_v10:
.space 4
label_61_v27:
.space 4
label_64_v28:
.space 4
label_104_v48:
.space 4
label_47_v19:
.space 4
label_66_v20:
.space 4
label_75_v31:
.space 4
label_100_v46:
.space 4
label_32_v12:
.space 4
label_33_v13:
.space 4
label_52_v22:
.space 4
label_60_v26:
.space 4
label_112_v52:
.space 4
label_101_v47:
.space 4
label_106_v40:
.space 4
label_93_v43:
.space 4
label_26_v0:
.space 4
label_123_v55:
.space 4
label_21_v7:
.space 4
# Allocated labels for virtual registers
label_169_v81:
.space 4
label_158_v76:
.space 4
label_136_v64:
.space 4
label_140_v66:
.space 4
label_194_v94:
.space 4
label_210_v100:
.space 4
label_231_v111:
.space 4
label_207_v101:
.space 4
label_213_v104:
.space 4
label_161_v77:
.space 4
label_211_v103:
.space 4
label_187_v91:
.space 4
label_144_v68:
.space 4
label_182_v88:
.space 4
label_258_v128:
.space 4
label_132_v62:
.space 4
label_229_v113:
.space 4
label_228_v112:
.space 4
label_165_v79:
.space 4
label_148_v70:
.space 4
label_245_v121:
.space 4
label_244_v118:
.space 4
label_192_v93:
.space 4
label_208_v102:
.space 4
label_153_v73:
.space 4
label_202_v96:
.space 4
label_223_v107:
.space 4
label_129_v60:
.space 4
label_241_v119:
.space 4
label_203_v99:
.space 4
label_195_v95:
.space 4
label_253_v125:
.space 4
label_171_v82:
.space 4
label_186_v90:
.space 4
label_145_v69:
.space 4
label_250_v124:
.space 4
label_179_v87:
.space 4
label_173_v83:
.space 4
label_166_v80:
.space 4
label_242_v120:
.space 4
label_216_v106:
.space 4
label_131_v61:
.space 4
label_168_v78:
.space 4
label_157_v75:
.space 4
label_234_v115:
.space 4
label_221_v109:
.space 4
label_237_v117:
.space 4
label_139_v63:
.space 4
label_200_v98:
.space 4
label_249_v123:
.space 4
label_174_v84:
.space 4
label_190_v92:
.space 4
label_220_v108:
.space 4
label_152_v72:
.space 4
label_181_v85:
.space 4
label_147_v67:
.space 4
label_199_v97:
.space 4
label_257_v127:
.space 4
label_232_v114:
.space 4
label_178_v86:
.space 4
label_252_v122:
.space 4
label_255_v126:
.space 4
label_189_v89:
.space 4
label_236_v116:
.space 4
label_224_v110:
.space 4
label_215_v105:
.space 4
label_150_v71:
.space 4
label_137_v65:
.space 4
label_160_v74:
.space 4

.text
fill_matrix:
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
# Original instruction: li v0,11
li $t5,11
la $t0,label_26_v0
sw $t5,0($t0)
# Original instruction: addiu v1,$fp,8
addiu $t5,$fp,8
la $t0,label_15_v1
sw $t5,0($t0)
# Original instruction: li v2,0
li $t5,0
la $t0,label_12_v2
sw $t5,0($t0)
# Original instruction: addiu v3,$zero,12
addiu $t5,$zero,12
la $t0,label_13_v3
sw $t5,0($t0)
# Original instruction: mul v4,v2,v3
la $t5,label_12_v2
lw $t5,0($t5)
la $t4,label_13_v3
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_16_v4
sw $t3,0($t0)
# Original instruction: add v5,v1,v4
la $t5,label_15_v1
lw $t5,0($t5)
la $t4,label_16_v4
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_23_v5
sw $t3,0($t0)
# Original instruction: li v6,0
li $t5,0
la $t0,label_20_v6
sw $t5,0($t0)
# Original instruction: addiu v7,$zero,4
addiu $t5,$zero,4
la $t0,label_21_v7
sw $t5,0($t0)
# Original instruction: mul v8,v6,v7
la $t5,label_20_v6
lw $t5,0($t5)
la $t4,label_21_v7
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_24_v8
sw $t3,0($t0)
# Original instruction: add v9,v5,v8
la $t5,label_23_v5
lw $t5,0($t5)
la $t4,label_24_v8
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_27_v9
sw $t3,0($t0)
# Original instruction: sw v0,0(v9)
la $t5,label_26_v0
lw $t5,0($t5)
la $t4,label_27_v9
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v10,12
li $t5,12
la $t0,label_46_v10
sw $t5,0($t0)
# Original instruction: addiu v11,$fp,8
addiu $t5,$fp,8
la $t0,label_35_v11
sw $t5,0($t0)
# Original instruction: li v12,0
li $t5,0
la $t0,label_32_v12
sw $t5,0($t0)
# Original instruction: addiu v13,$zero,12
addiu $t5,$zero,12
la $t0,label_33_v13
sw $t5,0($t0)
# Original instruction: mul v14,v12,v13
la $t5,label_32_v12
lw $t5,0($t5)
la $t4,label_33_v13
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_36_v14
sw $t3,0($t0)
# Original instruction: add v15,v11,v14
la $t5,label_35_v11
lw $t5,0($t5)
la $t4,label_36_v14
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_43_v15
sw $t3,0($t0)
# Original instruction: li v16,1
li $t5,1
la $t0,label_40_v16
sw $t5,0($t0)
# Original instruction: addiu v17,$zero,4
addiu $t5,$zero,4
la $t0,label_41_v17
sw $t5,0($t0)
# Original instruction: mul v18,v16,v17
la $t5,label_40_v16
lw $t5,0($t5)
la $t4,label_41_v17
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_44_v18
sw $t3,0($t0)
# Original instruction: add v19,v15,v18
la $t5,label_43_v15
lw $t5,0($t5)
la $t4,label_44_v18
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_47_v19
sw $t3,0($t0)
# Original instruction: sw v10,0(v19)
la $t5,label_46_v10
lw $t5,0($t5)
la $t4,label_47_v19
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v20,13
li $t5,13
la $t0,label_66_v20
sw $t5,0($t0)
# Original instruction: addiu v21,$fp,8
addiu $t5,$fp,8
la $t0,label_55_v21
sw $t5,0($t0)
# Original instruction: li v22,0
li $t5,0
la $t0,label_52_v22
sw $t5,0($t0)
# Original instruction: addiu v23,$zero,12
addiu $t5,$zero,12
la $t0,label_53_v23
sw $t5,0($t0)
# Original instruction: mul v24,v22,v23
la $t5,label_52_v22
lw $t5,0($t5)
la $t4,label_53_v23
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_56_v24
sw $t3,0($t0)
# Original instruction: add v25,v21,v24
la $t5,label_55_v21
lw $t5,0($t5)
la $t4,label_56_v24
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_63_v25
sw $t3,0($t0)
# Original instruction: li v26,2
li $t5,2
la $t0,label_60_v26
sw $t5,0($t0)
# Original instruction: addiu v27,$zero,4
addiu $t5,$zero,4
la $t0,label_61_v27
sw $t5,0($t0)
# Original instruction: mul v28,v26,v27
la $t5,label_60_v26
lw $t5,0($t5)
la $t4,label_61_v27
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_64_v28
sw $t3,0($t0)
# Original instruction: add v29,v25,v28
la $t5,label_63_v25
lw $t5,0($t5)
la $t4,label_64_v28
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_67_v29
sw $t3,0($t0)
# Original instruction: sw v20,0(v29)
la $t5,label_66_v20
lw $t5,0($t5)
la $t4,label_67_v29
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v30,21
li $t5,21
la $t0,label_86_v30
sw $t5,0($t0)
# Original instruction: addiu v31,$fp,8
addiu $t5,$fp,8
la $t0,label_75_v31
sw $t5,0($t0)
# Original instruction: li v32,1
li $t5,1
la $t0,label_72_v32
sw $t5,0($t0)
# Original instruction: addiu v33,$zero,12
addiu $t5,$zero,12
la $t0,label_73_v33
sw $t5,0($t0)
# Original instruction: mul v34,v32,v33
la $t5,label_72_v32
lw $t5,0($t5)
la $t4,label_73_v33
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_76_v34
sw $t3,0($t0)
# Original instruction: add v35,v31,v34
la $t5,label_75_v31
lw $t5,0($t5)
la $t4,label_76_v34
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_83_v35
sw $t3,0($t0)
# Original instruction: li v36,0
li $t5,0
la $t0,label_80_v36
sw $t5,0($t0)
# Original instruction: addiu v37,$zero,4
addiu $t5,$zero,4
la $t0,label_81_v37
sw $t5,0($t0)
# Original instruction: mul v38,v36,v37
la $t5,label_80_v36
lw $t5,0($t5)
la $t4,label_81_v37
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_84_v38
sw $t3,0($t0)
# Original instruction: add v39,v35,v38
la $t5,label_83_v35
lw $t5,0($t5)
la $t4,label_84_v38
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_87_v39
sw $t3,0($t0)
# Original instruction: sw v30,0(v39)
la $t5,label_86_v30
lw $t5,0($t5)
la $t4,label_87_v39
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v40,22
li $t5,22
la $t0,label_106_v40
sw $t5,0($t0)
# Original instruction: addiu v41,$fp,8
addiu $t5,$fp,8
la $t0,label_95_v41
sw $t5,0($t0)
# Original instruction: li v42,1
li $t5,1
la $t0,label_92_v42
sw $t5,0($t0)
# Original instruction: addiu v43,$zero,12
addiu $t5,$zero,12
la $t0,label_93_v43
sw $t5,0($t0)
# Original instruction: mul v44,v42,v43
la $t5,label_92_v42
lw $t5,0($t5)
la $t4,label_93_v43
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_96_v44
sw $t3,0($t0)
# Original instruction: add v45,v41,v44
la $t5,label_95_v41
lw $t5,0($t5)
la $t4,label_96_v44
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_103_v45
sw $t3,0($t0)
# Original instruction: li v46,1
li $t5,1
la $t0,label_100_v46
sw $t5,0($t0)
# Original instruction: addiu v47,$zero,4
addiu $t5,$zero,4
la $t0,label_101_v47
sw $t5,0($t0)
# Original instruction: mul v48,v46,v47
la $t5,label_100_v46
lw $t5,0($t5)
la $t4,label_101_v47
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_104_v48
sw $t3,0($t0)
# Original instruction: add v49,v45,v48
la $t5,label_103_v45
lw $t5,0($t5)
la $t4,label_104_v48
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_107_v49
sw $t3,0($t0)
# Original instruction: sw v40,0(v49)
la $t5,label_106_v40
lw $t5,0($t5)
la $t4,label_107_v49
lw $t4,0($t4)
sw $t5,0($t4)
# Original instruction: li v50,23
li $t5,23
la $t0,label_126_v50
sw $t5,0($t0)
# Original instruction: addiu v51,$fp,8
addiu $t5,$fp,8
la $t0,label_115_v51
sw $t5,0($t0)
# Original instruction: li v52,1
li $t5,1
la $t0,label_112_v52
sw $t5,0($t0)
# Original instruction: addiu v53,$zero,12
addiu $t5,$zero,12
la $t0,label_113_v53
sw $t5,0($t0)
# Original instruction: mul v54,v52,v53
la $t5,label_112_v52
lw $t5,0($t5)
la $t4,label_113_v53
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_116_v54
sw $t3,0($t0)
# Original instruction: add v55,v51,v54
la $t5,label_115_v51
lw $t5,0($t5)
la $t4,label_116_v54
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_123_v55
sw $t3,0($t0)
# Original instruction: li v56,2
li $t5,2
la $t0,label_120_v56
sw $t5,0($t0)
# Original instruction: addiu v57,$zero,4
addiu $t5,$zero,4
la $t0,label_121_v57
sw $t5,0($t0)
# Original instruction: mul v58,v56,v57
la $t5,label_120_v56
lw $t5,0($t5)
la $t4,label_121_v57
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_124_v58
sw $t3,0($t0)
# Original instruction: add v59,v55,v58
la $t5,label_123_v55
lw $t5,0($t5)
la $t4,label_124_v58
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_127_v59
sw $t3,0($t0)
# Original instruction: sw v50,0(v59)
la $t5,label_126_v50
lw $t5,0($t5)
la $t4,label_127_v59
lw $t4,0($t4)
sw $t5,0($t4)
label_5_return_fill_matrix:
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
# Original instruction: addiu $sp,$sp,-8
addiu $sp,$sp,-8
# Original instruction: sw $fp,4($sp)
sw $fp,4($sp)
# Original instruction: sw $ra,0($sp)
sw $ra,0($sp)
# Original instruction: add $fp,$sp,$zero
add $fp,$sp,$zero
# Original instruction: addiu $sp,$sp,-32
addiu $sp,$sp,-32
# Original instruction: addiu v60,$fp,-32
addiu $t5,$fp,-32
la $t0,label_129_v60
sw $t5,0($t0)
# Original instruction: lw v61,0(v60)
la $t5,label_129_v60
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_131_v61
sw $t4,0($t0)
# Original instruction: addiu $sp,$sp,-4
addiu $sp,$sp,-4
# Original instruction: sw v61,0($sp)
la $t5,label_131_v61
lw $t5,0($t5)
sw $t5,0($sp)
# Original instruction: jal fill_matrix
jal fill_matrix
# Original instruction: addiu $sp,$sp,4
addiu $sp,$sp,4
# Original instruction: add v62,$v0,$zero
add $t5,$v0,$zero
la $t0,label_132_v62
sw $t5,0($t0)
# Original instruction: addiu v63,$fp,-32
addiu $t5,$fp,-32
la $t0,label_139_v63
sw $t5,0($t0)
# Original instruction: li v64,0
li $t5,0
la $t0,label_136_v64
sw $t5,0($t0)
# Original instruction: addiu v65,$zero,12
addiu $t5,$zero,12
la $t0,label_137_v65
sw $t5,0($t0)
# Original instruction: mul v66,v64,v65
la $t5,label_136_v64
lw $t5,0($t5)
la $t4,label_137_v65
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_140_v66
sw $t3,0($t0)
# Original instruction: add v67,v63,v66
la $t5,label_139_v63
lw $t5,0($t5)
la $t4,label_140_v66
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_147_v67
sw $t3,0($t0)
# Original instruction: li v68,0
li $t5,0
la $t0,label_144_v68
sw $t5,0($t0)
# Original instruction: addiu v69,$zero,4
addiu $t5,$zero,4
la $t0,label_145_v69
sw $t5,0($t0)
# Original instruction: mul v70,v68,v69
la $t5,label_144_v68
lw $t5,0($t5)
la $t4,label_145_v69
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_148_v70
sw $t3,0($t0)
# Original instruction: add v71,v67,v70
la $t5,label_147_v67
lw $t5,0($t5)
la $t4,label_148_v70
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_150_v71
sw $t3,0($t0)
# Original instruction: lw v72,0(v71)
la $t5,label_150_v71
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_152_v72
sw $t4,0($t0)
# Original instruction: add $a0,v72,$zero
la $t5,label_152_v72
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v73,$zero,$zero
add $t5,$zero,$zero
la $t0,label_153_v73
sw $t5,0($t0)
# Original instruction: addiu v74,$fp,-32
addiu $t5,$fp,-32
la $t0,label_160_v74
sw $t5,0($t0)
# Original instruction: li v75,0
li $t5,0
la $t0,label_157_v75
sw $t5,0($t0)
# Original instruction: addiu v76,$zero,12
addiu $t5,$zero,12
la $t0,label_158_v76
sw $t5,0($t0)
# Original instruction: mul v77,v75,v76
la $t5,label_157_v75
lw $t5,0($t5)
la $t4,label_158_v76
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_161_v77
sw $t3,0($t0)
# Original instruction: add v78,v74,v77
la $t5,label_160_v74
lw $t5,0($t5)
la $t4,label_161_v77
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_168_v78
sw $t3,0($t0)
# Original instruction: li v79,1
li $t5,1
la $t0,label_165_v79
sw $t5,0($t0)
# Original instruction: addiu v80,$zero,4
addiu $t5,$zero,4
la $t0,label_166_v80
sw $t5,0($t0)
# Original instruction: mul v81,v79,v80
la $t5,label_165_v79
lw $t5,0($t5)
la $t4,label_166_v80
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_169_v81
sw $t3,0($t0)
# Original instruction: add v82,v78,v81
la $t5,label_168_v78
lw $t5,0($t5)
la $t4,label_169_v81
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_171_v82
sw $t3,0($t0)
# Original instruction: lw v83,0(v82)
la $t5,label_171_v82
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_173_v83
sw $t4,0($t0)
# Original instruction: add $a0,v83,$zero
la $t5,label_173_v83
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v84,$zero,$zero
add $t5,$zero,$zero
la $t0,label_174_v84
sw $t5,0($t0)
# Original instruction: addiu v85,$fp,-32
addiu $t5,$fp,-32
la $t0,label_181_v85
sw $t5,0($t0)
# Original instruction: li v86,0
li $t5,0
la $t0,label_178_v86
sw $t5,0($t0)
# Original instruction: addiu v87,$zero,12
addiu $t5,$zero,12
la $t0,label_179_v87
sw $t5,0($t0)
# Original instruction: mul v88,v86,v87
la $t5,label_178_v86
lw $t5,0($t5)
la $t4,label_179_v87
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_182_v88
sw $t3,0($t0)
# Original instruction: add v89,v85,v88
la $t5,label_181_v85
lw $t5,0($t5)
la $t4,label_182_v88
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_189_v89
sw $t3,0($t0)
# Original instruction: li v90,2
li $t5,2
la $t0,label_186_v90
sw $t5,0($t0)
# Original instruction: addiu v91,$zero,4
addiu $t5,$zero,4
la $t0,label_187_v91
sw $t5,0($t0)
# Original instruction: mul v92,v90,v91
la $t5,label_186_v90
lw $t5,0($t5)
la $t4,label_187_v91
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_190_v92
sw $t3,0($t0)
# Original instruction: add v93,v89,v92
la $t5,label_189_v89
lw $t5,0($t5)
la $t4,label_190_v92
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_192_v93
sw $t3,0($t0)
# Original instruction: lw v94,0(v93)
la $t5,label_192_v93
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_194_v94
sw $t4,0($t0)
# Original instruction: add $a0,v94,$zero
la $t5,label_194_v94
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v95,$zero,$zero
add $t5,$zero,$zero
la $t0,label_195_v95
sw $t5,0($t0)
# Original instruction: addiu v96,$fp,-32
addiu $t5,$fp,-32
la $t0,label_202_v96
sw $t5,0($t0)
# Original instruction: li v97,1
li $t5,1
la $t0,label_199_v97
sw $t5,0($t0)
# Original instruction: addiu v98,$zero,12
addiu $t5,$zero,12
la $t0,label_200_v98
sw $t5,0($t0)
# Original instruction: mul v99,v97,v98
la $t5,label_199_v97
lw $t5,0($t5)
la $t4,label_200_v98
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_203_v99
sw $t3,0($t0)
# Original instruction: add v100,v96,v99
la $t5,label_202_v96
lw $t5,0($t5)
la $t4,label_203_v99
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_210_v100
sw $t3,0($t0)
# Original instruction: li v101,0
li $t5,0
la $t0,label_207_v101
sw $t5,0($t0)
# Original instruction: addiu v102,$zero,4
addiu $t5,$zero,4
la $t0,label_208_v102
sw $t5,0($t0)
# Original instruction: mul v103,v101,v102
la $t5,label_207_v101
lw $t5,0($t5)
la $t4,label_208_v102
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_211_v103
sw $t3,0($t0)
# Original instruction: add v104,v100,v103
la $t5,label_210_v100
lw $t5,0($t5)
la $t4,label_211_v103
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_213_v104
sw $t3,0($t0)
# Original instruction: lw v105,0(v104)
la $t5,label_213_v104
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_215_v105
sw $t4,0($t0)
# Original instruction: add $a0,v105,$zero
la $t5,label_215_v105
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v106,$zero,$zero
add $t5,$zero,$zero
la $t0,label_216_v106
sw $t5,0($t0)
# Original instruction: addiu v107,$fp,-32
addiu $t5,$fp,-32
la $t0,label_223_v107
sw $t5,0($t0)
# Original instruction: li v108,1
li $t5,1
la $t0,label_220_v108
sw $t5,0($t0)
# Original instruction: addiu v109,$zero,12
addiu $t5,$zero,12
la $t0,label_221_v109
sw $t5,0($t0)
# Original instruction: mul v110,v108,v109
la $t5,label_220_v108
lw $t5,0($t5)
la $t4,label_221_v109
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_224_v110
sw $t3,0($t0)
# Original instruction: add v111,v107,v110
la $t5,label_223_v107
lw $t5,0($t5)
la $t4,label_224_v110
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_231_v111
sw $t3,0($t0)
# Original instruction: li v112,1
li $t5,1
la $t0,label_228_v112
sw $t5,0($t0)
# Original instruction: addiu v113,$zero,4
addiu $t5,$zero,4
la $t0,label_229_v113
sw $t5,0($t0)
# Original instruction: mul v114,v112,v113
la $t5,label_228_v112
lw $t5,0($t5)
la $t4,label_229_v113
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_232_v114
sw $t3,0($t0)
# Original instruction: add v115,v111,v114
la $t5,label_231_v111
lw $t5,0($t5)
la $t4,label_232_v114
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_234_v115
sw $t3,0($t0)
# Original instruction: lw v116,0(v115)
la $t5,label_234_v115
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_236_v116
sw $t4,0($t0)
# Original instruction: add $a0,v116,$zero
la $t5,label_236_v116
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v117,$zero,$zero
add $t5,$zero,$zero
la $t0,label_237_v117
sw $t5,0($t0)
# Original instruction: addiu v118,$fp,-32
addiu $t5,$fp,-32
la $t0,label_244_v118
sw $t5,0($t0)
# Original instruction: li v119,1
li $t5,1
la $t0,label_241_v119
sw $t5,0($t0)
# Original instruction: addiu v120,$zero,12
addiu $t5,$zero,12
la $t0,label_242_v120
sw $t5,0($t0)
# Original instruction: mul v121,v119,v120
la $t5,label_241_v119
lw $t5,0($t5)
la $t4,label_242_v120
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_245_v121
sw $t3,0($t0)
# Original instruction: add v122,v118,v121
la $t5,label_244_v118
lw $t5,0($t5)
la $t4,label_245_v121
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_252_v122
sw $t3,0($t0)
# Original instruction: li v123,2
li $t5,2
la $t0,label_249_v123
sw $t5,0($t0)
# Original instruction: addiu v124,$zero,4
addiu $t5,$zero,4
la $t0,label_250_v124
sw $t5,0($t0)
# Original instruction: mul v125,v123,v124
la $t5,label_249_v123
lw $t5,0($t5)
la $t4,label_250_v124
lw $t4,0($t4)
mul $t3,$t5,$t4
la $t0,label_253_v125
sw $t3,0($t0)
# Original instruction: add v126,v122,v125
la $t5,label_252_v122
lw $t5,0($t5)
la $t4,label_253_v125
lw $t4,0($t4)
add $t3,$t5,$t4
la $t0,label_255_v126
sw $t3,0($t0)
# Original instruction: lw v127,0(v126)
la $t5,label_255_v126
lw $t5,0($t5)
lw $t4,0($t5)
la $t0,label_257_v127
sw $t4,0($t0)
# Original instruction: add $a0,v127,$zero
la $t5,label_257_v127
lw $t5,0($t5)
add $a0,$t5,$zero
# Original instruction: li $v0,1
li $v0,1
# Original instruction: syscall
syscall
# Original instruction: add v128,$zero,$zero
add $t5,$zero,$zero
la $t0,label_258_v128
sw $t5,0($t0)
label_7_return_main:
# Original instruction: addiu $sp,$sp,32
addiu $sp,$sp,32
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

