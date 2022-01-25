// Generated from com\alloyprofiling\ALLOY.g4 by ANTLR 4.7.1

 package com.alloyprofiling;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ALLOYParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, T__67=68, T__68=69, T__69=70, T__70=71, T__71=72, T__72=73, 
		T__73=74, T__74=75, T__75=76, T__76=77, T__77=78, T__78=79, T__79=80, 
		DIGIT=81, ID=82, ALPHA=83, WS=84, COMMENT=85, LINE_COMMENT=86, STRING=87;
	public static final int
		RULE_specification = 0, RULE_param_module = 1, RULE_module = 2, RULE_names_opt = 3, 
		RULE_as_name_opt = 4, RULE_para_open = 5, RULE_open = 6, RULE_macro_expr = 7, 
		RULE_macro = 8, RULE_paragraph = 9, RULE_factDecl = 10, RULE_name_opt = 11, 
		RULE_assertDecl = 12, RULE_nameID = 13, RULE_decls_f = 14, RULE_funExpr = 15, 
		RULE_funDecl = 16, RULE_nameOrBlock = 17, RULE_name_cmd_opt = 18, RULE_scope_opt = 19, 
		RULE_run_or_check = 20, RULE_cmdDecl = 21, RULE_paraDecls = 22, RULE_paraDecls_opt = 23, 
		RULE_predDecl = 24, RULE_typescopes = 25, RULE_but_typescopes = 26, RULE_expect_digit = 27, 
		RULE_scope = 28, RULE_exactly_opt = 29, RULE_typescope = 30, RULE_decls = 31, 
		RULE_multiplicity = 32, RULE_abs = 33, RULE_priv = 34, RULE_abs_multiplicity = 35, 
		RULE_sigDecl = 36, RULE_names = 37, RULE_enumDecl = 38, RULE_mult = 39, 
		RULE_union = 40, RULE_superSet = 41, RULE_sigExt = 42, RULE_sigExtension = 43, 
		RULE_exprs = 44, RULE_notOp = 45, RULE_decls_e = 46, RULE_expr = 47, RULE_num = 48, 
		RULE_const_sets = 49, RULE_constant = 50, RULE_disjFunc = 51, RULE_disjoint = 52, 
		RULE_disj = 53, RULE_comma_opt = 54, RULE_decl = 55, RULE_letDecl = 56, 
		RULE_quant = 57, RULE_setCard = 58, RULE_tcOp = 59, RULE_unOp = 60, RULE_bit_shifter_operators = 61, 
		RULE_dotOp = 62, RULE_add = 63, RULE_sub = 64, RULE_binOp = 65, RULE_mult_or_set = 66, 
		RULE_arrowOp = 67, RULE_rel_operators = 68, RULE_compareOp = 69, RULE_block = 70, 
		RULE_block_opt = 71, RULE_blockOrBar = 72, RULE_bar = 73, RULE_name = 74, 
		RULE_number = 75;
	public static final String[] ruleNames = {
		"specification", "param_module", "module", "names_opt", "as_name_opt", 
		"para_open", "open", "macro_expr", "macro", "paragraph", "factDecl", "name_opt", 
		"assertDecl", "nameID", "decls_f", "funExpr", "funDecl", "nameOrBlock", 
		"name_cmd_opt", "scope_opt", "run_or_check", "cmdDecl", "paraDecls", "paraDecls_opt", 
		"predDecl", "typescopes", "but_typescopes", "expect_digit", "scope", "exactly_opt", 
		"typescope", "decls", "multiplicity", "abs", "priv", "abs_multiplicity", 
		"sigDecl", "names", "enumDecl", "mult", "union", "superSet", "sigExt", 
		"sigExtension", "exprs", "notOp", "decls_e", "expr", "num", "const_sets", 
		"constant", "disjFunc", "disjoint", "disj", "comma_opt", "decl", "letDecl", 
		"quant", "setCard", "tcOp", "unOp", "bit_shifter_operators", "dotOp", 
		"add", "sub", "binOp", "mult_or_set", "arrowOp", "rel_operators", "compareOp", 
		"block", "block_opt", "blockOrBar", "bar", "name", "number"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'['", "'exactly'", "','", "']'", "'module'", "'as'", "'open'", 
		"'='", "'{'", "'}'", "'let'", "'fact'", "'assert'", "'.'", "'fun'", "':'", 
		"'run'", "'check'", "'('", "')'", "'private'", "'pred'", "'but'", "'expect'", 
		"'for'", "'seq'", "'int'", "'abstract'", "'sig'", "'enum'", "'lone'", 
		"'one'", "'some'", "'+'", "'extends'", "'in'", "'!'", "'not'", "'=>'", 
		"'implies'", "'else'", "'sum'", "'seq/Int'", "'@'", "'this'", "'-'", "'none'", 
		"'univ'", "'iden'", "'disj'", "'disjoint'", "'all'", "'no'", "'#'", "'*'", 
		"'^'", "'set'", "'~'", "'<<'", "'>>'", "'>>>'", "'||'", "'or'", "'&&'", 
		"'and'", "'<=>'", "'iff'", "'&'", "'++'", "'<:'", "':>'", "'->'", "'<'", 
		"'>'", "'=<'", "'>='", "'<='", "'|'", "'this/'", "'/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "DIGIT", "ID", "ALPHA", 
		"WS", "COMMENT", "LINE_COMMENT", "STRING"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ALLOY.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ALLOYParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SpecificationContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ALLOYParser.EOF, 0); }
		public List<ModuleContext> module() {
			return getRuleContexts(ModuleContext.class);
		}
		public ModuleContext module(int i) {
			return getRuleContext(ModuleContext.class,i);
		}
		public List<OpenContext> open() {
			return getRuleContexts(OpenContext.class);
		}
		public OpenContext open(int i) {
			return getRuleContext(OpenContext.class,i);
		}
		public List<ParagraphContext> paragraph() {
			return getRuleContexts(ParagraphContext.class);
		}
		public ParagraphContext paragraph(int i) {
			return getRuleContext(ParagraphContext.class,i);
		}
		public SpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitSpecification(this);
		}
	}

	public final SpecificationContext specification() throws RecognitionException {
		SpecificationContext _localctx = new SpecificationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_specification);
		int _la;
		try {
			int _alt;
			setState(172);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(152);
					module();
					}
					break;
				}
				setState(158);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(155);
						open();
						}
						} 
					}
					setState(160);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__4) {
					{
					setState(161);
					module();
					}
				}

				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__14) | (1L << T__16) | (1L << T__17) | (1L << T__20) | (1L << T__21) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32))) != 0) || _la==T__78 || _la==ID) {
					{
					{
					setState(164);
					paragraph();
					}
					}
					setState(169);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(170);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				match(EOF);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_moduleContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public Param_moduleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_module; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterParam_module(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitParam_module(this);
		}
	}

	public final Param_moduleContext param_module() throws RecognitionException {
		Param_moduleContext _localctx = new Param_moduleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_param_module);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(174);
				match(T__0);
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(175);
					match(T__1);
					}
				}

				setState(178);
				name();
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(179);
					match(T__2);
					setState(181);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__1) {
						{
						setState(180);
						match(T__1);
						}
					}

					setState(183);
					name();
					}
					}
					setState(188);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(189);
				match(T__3);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModuleContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Param_moduleContext param_module() {
			return getRuleContext(Param_moduleContext.class,0);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterModule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitModule(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_module);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(T__4);
			setState(194);
			name();
			setState(195);
			param_module();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Names_optContext extends ParserRuleContext {
		public NamesContext names() {
			return getRuleContext(NamesContext.class,0);
		}
		public Names_optContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_names_opt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterNames_opt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitNames_opt(this);
		}
	}

	public final Names_optContext names_opt() throws RecognitionException {
		Names_optContext _localctx = new Names_optContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_names_opt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__78 || _la==ID) {
				{
				setState(197);
				names();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class As_name_optContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public As_name_optContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_as_name_opt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterAs_name_opt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitAs_name_opt(this);
		}
	}

	public final As_name_optContext as_name_opt() throws RecognitionException {
		As_name_optContext _localctx = new As_name_optContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_as_name_opt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(200);
				match(T__5);
				setState(201);
				name();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Para_openContext extends ParserRuleContext {
		public Names_optContext names_opt() {
			return getRuleContext(Names_optContext.class,0);
		}
		public Para_openContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_para_open; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterPara_open(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitPara_open(this);
		}
	}

	public final Para_openContext para_open() throws RecognitionException {
		Para_openContext _localctx = new Para_openContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_para_open);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(204);
				match(T__0);
				setState(205);
				names_opt();
				setState(206);
				match(T__3);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OpenContext extends ParserRuleContext {
		public PrivContext priv() {
			return getRuleContext(PrivContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Para_openContext para_open() {
			return getRuleContext(Para_openContext.class,0);
		}
		public As_name_optContext as_name_opt() {
			return getRuleContext(As_name_optContext.class,0);
		}
		public OpenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_open; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterOpen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitOpen(this);
		}
	}

	public final OpenContext open() throws RecognitionException {
		OpenContext _localctx = new OpenContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_open);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			priv();
			setState(211);
			match(T__6);
			setState(212);
			name();
			setState(213);
			para_open();
			setState(214);
			as_name_opt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Macro_exprContext extends ParserRuleContext {
		public DeclsContext decls() {
			return getRuleContext(DeclsContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Macro_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macro_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterMacro_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitMacro_expr(this);
		}
	}

	public final Macro_exprContext macro_expr() throws RecognitionException {
		Macro_exprContext _localctx = new Macro_exprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_macro_expr);
		int _la;
		try {
			setState(235);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(216);
					match(T__7);
					}
				}

				setState(219);
				match(T__8);
				setState(227);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(223);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__10) | (1L << T__18) | (1L << T__25) | (1L << T__26) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__36) | (1L << T__37) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57))) != 0) || ((((_la - 79)) & ~0x3f) == 0 && ((1L << (_la - 79)) & ((1L << (T__78 - 79)) | (1L << (DIGIT - 79)) | (1L << (ID - 79)) | (1L << (STRING - 79)))) != 0)) {
						{
						{
						setState(220);
						expr(0);
						}
						}
						setState(225);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case 2:
					{
					setState(226);
					decls();
					}
					break;
				}
				setState(229);
				match(T__9);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(230);
				match(T__7);
				setState(233);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(231);
					expr(0);
					}
					break;
				case 2:
					{
					setState(232);
					decls();
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MacroContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Macro_exprContext macro_expr() {
			return getRuleContext(Macro_exprContext.class,0);
		}
		public NamesContext names() {
			return getRuleContext(NamesContext.class,0);
		}
		public MacroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterMacro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitMacro(this);
		}
	}

	public final MacroContext macro() throws RecognitionException {
		MacroContext _localctx = new MacroContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_macro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(T__10);
			setState(238);
			name();
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(239);
				match(T__0);
				setState(240);
				names();
				setState(241);
				match(T__3);
				}
			}

			setState(245);
			macro_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParagraphContext extends ParserRuleContext {
		public FactDeclContext factDecl() {
			return getRuleContext(FactDeclContext.class,0);
		}
		public AssertDeclContext assertDecl() {
			return getRuleContext(AssertDeclContext.class,0);
		}
		public FunDeclContext funDecl() {
			return getRuleContext(FunDeclContext.class,0);
		}
		public CmdDeclContext cmdDecl() {
			return getRuleContext(CmdDeclContext.class,0);
		}
		public EnumDeclContext enumDecl() {
			return getRuleContext(EnumDeclContext.class,0);
		}
		public SigDeclContext sigDecl() {
			return getRuleContext(SigDeclContext.class,0);
		}
		public PredDeclContext predDecl() {
			return getRuleContext(PredDeclContext.class,0);
		}
		public MacroContext macro() {
			return getRuleContext(MacroContext.class,0);
		}
		public ParagraphContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paragraph; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterParagraph(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitParagraph(this);
		}
	}

	public final ParagraphContext paragraph() throws RecognitionException {
		ParagraphContext _localctx = new ParagraphContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_paragraph);
		try {
			setState(255);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(247);
				factDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(248);
				assertDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(249);
				funDecl();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(250);
				cmdDecl();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(251);
				enumDecl();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(252);
				sigDecl();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(253);
				predDecl();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(254);
				macro();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactDeclContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public FactDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterFactDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitFactDecl(this);
		}
	}

	public final FactDeclContext factDecl() throws RecognitionException {
		FactDeclContext _localctx = new FactDeclContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_factDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(T__11);
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__78 || _la==ID) {
				{
				setState(258);
				name();
				}
			}

			setState(261);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Name_optContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Name_optContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name_opt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterName_opt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitName_opt(this);
		}
	}

	public final Name_optContext name_opt() throws RecognitionException {
		Name_optContext _localctx = new Name_optContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_name_opt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__78 || _la==ID) {
				{
				setState(263);
				name();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssertDeclContext extends ParserRuleContext {
		public Name_optContext name_opt() {
			return getRuleContext(Name_optContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public AssertDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assertDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterAssertDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitAssertDecl(this);
		}
	}

	public final AssertDeclContext assertDecl() throws RecognitionException {
		AssertDeclContext _localctx = new AssertDeclContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_assertDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(T__12);
			setState(267);
			name_opt();
			setState(268);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameIDContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode ID() { return getToken(ALLOYParser.ID, 0); }
		public NameIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nameID; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterNameID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitNameID(this);
		}
	}

	public final NameIDContext nameID() throws RecognitionException {
		NameIDContext _localctx = new NameIDContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_nameID);
		try {
			setState(275);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				name();
				setState(271);
				match(T__13);
				setState(272);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(274);
				match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Decls_fContext extends ParserRuleContext {
		public DeclsContext decls() {
			return getRuleContext(DeclsContext.class,0);
		}
		public Decls_fContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decls_f; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterDecls_f(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitDecls_f(this);
		}
	}

	public final Decls_fContext decls_f() throws RecognitionException {
		Decls_fContext _localctx = new Decls_fContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_decls_f);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(T__8);
			setState(278);
			decls();
			setState(279);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunExprContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FunExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterFunExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitFunExpr(this);
		}
	}

	public final FunExprContext funExpr() throws RecognitionException {
		FunExprContext _localctx = new FunExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_funExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunDeclContext extends ParserRuleContext {
		public PrivContext priv() {
			return getRuleContext(PrivContext.class,0);
		}
		public NameIDContext nameID() {
			return getRuleContext(NameIDContext.class,0);
		}
		public ParaDecls_optContext paraDecls_opt() {
			return getRuleContext(ParaDecls_optContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FunExprContext funExpr() {
			return getRuleContext(FunExprContext.class,0);
		}
		public FunDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterFunDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitFunDecl(this);
		}
	}

	public final FunDeclContext funDecl() throws RecognitionException {
		FunDeclContext _localctx = new FunDeclContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_funDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			priv();
			setState(284);
			match(T__14);
			setState(285);
			nameID();
			setState(286);
			paraDecls_opt();
			setState(287);
			match(T__15);
			setState(288);
			expr(0);
			setState(289);
			match(T__8);
			setState(290);
			funExpr();
			setState(291);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameOrBlockContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public NameIDContext nameID() {
			return getRuleContext(NameIDContext.class,0);
		}
		public NameOrBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nameOrBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterNameOrBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitNameOrBlock(this);
		}
	}

	public final NameOrBlockContext nameOrBlock() throws RecognitionException {
		NameOrBlockContext _localctx = new NameOrBlockContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_nameOrBlock);
		try {
			setState(302);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(295);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(293);
					name();
					}
					break;
				case 2:
					{
					setState(294);
					block();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(300);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(297);
					nameID();
					setState(298);
					block();
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Name_cmd_optContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Name_cmd_optContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name_cmd_opt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterName_cmd_opt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitName_cmd_opt(this);
		}
	}

	public final Name_cmd_optContext name_cmd_opt() throws RecognitionException {
		Name_cmd_optContext _localctx = new Name_cmd_optContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_name_cmd_opt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__78 || _la==ID) {
				{
				setState(304);
				name();
				setState(305);
				match(T__15);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Scope_optContext extends ParserRuleContext {
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public Scope_optContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scope_opt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterScope_opt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitScope_opt(this);
		}
	}

	public final Scope_optContext scope_opt() throws RecognitionException {
		Scope_optContext _localctx = new Scope_optContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_scope_opt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23 || _la==T__24) {
				{
				setState(309);
				scope();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Run_or_checkContext extends ParserRuleContext {
		public Run_or_checkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_run_or_check; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterRun_or_check(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitRun_or_check(this);
		}
	}

	public final Run_or_checkContext run_or_check() throws RecognitionException {
		Run_or_checkContext _localctx = new Run_or_checkContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_run_or_check);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			_la = _input.LA(1);
			if ( !(_la==T__16 || _la==T__17) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdDeclContext extends ParserRuleContext {
		public Name_cmd_optContext name_cmd_opt() {
			return getRuleContext(Name_cmd_optContext.class,0);
		}
		public Run_or_checkContext run_or_check() {
			return getRuleContext(Run_or_checkContext.class,0);
		}
		public NameOrBlockContext nameOrBlock() {
			return getRuleContext(NameOrBlockContext.class,0);
		}
		public Scope_optContext scope_opt() {
			return getRuleContext(Scope_optContext.class,0);
		}
		public CmdDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterCmdDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitCmdDecl(this);
		}
	}

	public final CmdDeclContext cmdDecl() throws RecognitionException {
		CmdDeclContext _localctx = new CmdDeclContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_cmdDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			name_cmd_opt();
			setState(315);
			run_or_check();
			setState(316);
			nameOrBlock();
			setState(317);
			scope_opt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParaDeclsContext extends ParserRuleContext {
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ParaDeclsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paraDecls; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterParaDecls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitParaDecls(this);
		}
	}

	public final ParaDeclsContext paraDecls() throws RecognitionException {
		ParaDeclsContext _localctx = new ParaDeclsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_paraDecls);
		int _la;
		try {
			setState(343);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				match(T__18);
				setState(328);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 21)) & ~0x3f) == 0 && ((1L << (_la - 21)) & ((1L << (T__20 - 21)) | (1L << (T__49 - 21)) | (1L << (T__50 - 21)) | (1L << (T__78 - 21)) | (1L << (ID - 21)))) != 0)) {
					{
					setState(320);
					decl();
					setState(325);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__2) {
						{
						{
						setState(321);
						match(T__2);
						setState(322);
						decl();
						}
						}
						setState(327);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(330);
				match(T__19);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(331);
				match(T__0);
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 21)) & ~0x3f) == 0 && ((1L << (_la - 21)) & ((1L << (T__20 - 21)) | (1L << (T__49 - 21)) | (1L << (T__50 - 21)) | (1L << (T__78 - 21)) | (1L << (ID - 21)))) != 0)) {
					{
					setState(332);
					decl();
					setState(337);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__2) {
						{
						{
						setState(333);
						match(T__2);
						setState(334);
						decl();
						}
						}
						setState(339);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(342);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParaDecls_optContext extends ParserRuleContext {
		public ParaDeclsContext paraDecls() {
			return getRuleContext(ParaDeclsContext.class,0);
		}
		public ParaDecls_optContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paraDecls_opt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterParaDecls_opt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitParaDecls_opt(this);
		}
	}

	public final ParaDecls_optContext paraDecls_opt() throws RecognitionException {
		ParaDecls_optContext _localctx = new ParaDecls_optContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_paraDecls_opt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0 || _la==T__18) {
				{
				setState(345);
				paraDecls();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredDeclContext extends ParserRuleContext {
		public NameIDContext nameID() {
			return getRuleContext(NameIDContext.class,0);
		}
		public ParaDecls_optContext paraDecls_opt() {
			return getRuleContext(ParaDecls_optContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public PredDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterPredDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitPredDecl(this);
		}
	}

	public final PredDeclContext predDecl() throws RecognitionException {
		PredDeclContext _localctx = new PredDeclContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_predDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__20) {
				{
				setState(348);
				match(T__20);
				}
			}

			setState(351);
			match(T__21);
			setState(352);
			nameID();
			setState(353);
			paraDecls_opt();
			setState(354);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypescopesContext extends ParserRuleContext {
		public List<TypescopeContext> typescope() {
			return getRuleContexts(TypescopeContext.class);
		}
		public TypescopeContext typescope(int i) {
			return getRuleContext(TypescopeContext.class,i);
		}
		public TypescopesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typescopes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterTypescopes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitTypescopes(this);
		}
	}

	public final TypescopesContext typescopes() throws RecognitionException {
		TypescopesContext _localctx = new TypescopesContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_typescopes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			typescope();
			setState(361);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(357);
				match(T__2);
				setState(358);
				typescope();
				}
				}
				setState(363);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class But_typescopesContext extends ParserRuleContext {
		public TypescopesContext typescopes() {
			return getRuleContext(TypescopesContext.class,0);
		}
		public But_typescopesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_but_typescopes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterBut_typescopes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitBut_typescopes(this);
		}
	}

	public final But_typescopesContext but_typescopes() throws RecognitionException {
		But_typescopesContext _localctx = new But_typescopesContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_but_typescopes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(364);
				match(T__22);
				setState(365);
				typescopes();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expect_digitContext extends ParserRuleContext {
		public TerminalNode DIGIT() { return getToken(ALLOYParser.DIGIT, 0); }
		public Expect_digitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expect_digit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterExpect_digit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitExpect_digit(this);
		}
	}

	public final Expect_digitContext expect_digit() throws RecognitionException {
		Expect_digitContext _localctx = new Expect_digitContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_expect_digit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(368);
				match(T__23);
				setState(369);
				match(DIGIT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScopeContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public But_typescopesContext but_typescopes() {
			return getRuleContext(But_typescopesContext.class,0);
		}
		public Expect_digitContext expect_digit() {
			return getRuleContext(Expect_digitContext.class,0);
		}
		public TypescopesContext typescopes() {
			return getRuleContext(TypescopesContext.class,0);
		}
		public TerminalNode DIGIT() { return getToken(ALLOYParser.DIGIT, 0); }
		public ScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterScope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitScope(this);
		}
	}

	public final ScopeContext scope() throws RecognitionException {
		ScopeContext _localctx = new ScopeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_scope);
		try {
			setState(383);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(372);
				match(T__24);
				setState(373);
				number();
				setState(374);
				but_typescopes();
				setState(375);
				expect_digit();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(377);
				match(T__24);
				setState(378);
				typescopes();
				setState(379);
				expect_digit();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(381);
				match(T__23);
				setState(382);
				match(DIGIT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Exactly_optContext extends ParserRuleContext {
		public Exactly_optContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exactly_opt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterExactly_opt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitExactly_opt(this);
		}
	}

	public final Exactly_optContext exactly_opt() throws RecognitionException {
		Exactly_optContext _localctx = new Exactly_optContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_exactly_opt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(385);
				match(T__1);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypescopeContext extends ParserRuleContext {
		public Exactly_optContext exactly_opt() {
			return getRuleContext(Exactly_optContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TypescopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typescope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterTypescope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitTypescope(this);
		}
	}

	public final TypescopeContext typescope() throws RecognitionException {
		TypescopeContext _localctx = new TypescopeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_typescope);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			exactly_opt();
			setState(389);
			number();
			setState(393);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__78:
			case ID:
				{
				setState(390);
				name();
				}
				break;
			case T__25:
				{
				setState(391);
				match(T__25);
				}
				break;
			case T__26:
				{
				setState(392);
				match(T__26);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclsContext extends ParserRuleContext {
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public DeclsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decls; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterDecls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitDecls(this);
		}
	}

	public final DeclsContext decls() throws RecognitionException {
		DeclsContext _localctx = new DeclsContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_decls);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(396);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(395);
					match(T__2);
					}
				}

				setState(398);
				decl();
				setState(403);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(399);
					match(T__2);
					setState(400);
					decl();
					}
					}
					setState(405);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicityContext extends ParserRuleContext {
		public MultContext mult() {
			return getRuleContext(MultContext.class,0);
		}
		public MultiplicityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterMultiplicity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitMultiplicity(this);
		}
	}

	public final MultiplicityContext multiplicity() throws RecognitionException {
		MultiplicityContext _localctx = new MultiplicityContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_multiplicity);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(409);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__30) | (1L << T__31) | (1L << T__32))) != 0)) {
				{
				setState(408);
				mult();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbsContext extends ParserRuleContext {
		public AbsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterAbs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitAbs(this);
		}
	}

	public final AbsContext abs() throws RecognitionException {
		AbsContext _localctx = new AbsContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_abs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(411);
				match(T__27);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrivContext extends ParserRuleContext {
		public PrivContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_priv; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterPriv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitPriv(this);
		}
	}

	public final PrivContext priv() throws RecognitionException {
		PrivContext _localctx = new PrivContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_priv);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__20) {
				{
				setState(414);
				match(T__20);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Abs_multiplicityContext extends ParserRuleContext {
		public AbsContext abs() {
			return getRuleContext(AbsContext.class,0);
		}
		public MultiplicityContext multiplicity() {
			return getRuleContext(MultiplicityContext.class,0);
		}
		public Abs_multiplicityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abs_multiplicity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterAbs_multiplicity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitAbs_multiplicity(this);
		}
	}

	public final Abs_multiplicityContext abs_multiplicity() throws RecognitionException {
		Abs_multiplicityContext _localctx = new Abs_multiplicityContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_abs_multiplicity);
		try {
			setState(423);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(417);
				abs();
				setState(418);
				multiplicity();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(420);
				multiplicity();
				setState(421);
				abs();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SigDeclContext extends ParserRuleContext {
		public PrivContext priv() {
			return getRuleContext(PrivContext.class,0);
		}
		public Abs_multiplicityContext abs_multiplicity() {
			return getRuleContext(Abs_multiplicityContext.class,0);
		}
		public NamesContext names() {
			return getRuleContext(NamesContext.class,0);
		}
		public SigExtensionContext sigExtension() {
			return getRuleContext(SigExtensionContext.class,0);
		}
		public DeclsContext decls() {
			return getRuleContext(DeclsContext.class,0);
		}
		public Block_optContext block_opt() {
			return getRuleContext(Block_optContext.class,0);
		}
		public SigDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sigDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterSigDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitSigDecl(this);
		}
	}

	public final SigDeclContext sigDecl() throws RecognitionException {
		SigDeclContext _localctx = new SigDeclContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_sigDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(425);
			priv();
			setState(426);
			abs_multiplicity();
			setState(427);
			match(T__28);
			setState(428);
			names();
			setState(429);
			sigExtension();
			setState(430);
			match(T__8);
			setState(431);
			decls();
			setState(432);
			match(T__9);
			setState(433);
			block_opt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamesContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public NamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_names; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterNames(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitNames(this);
		}
	}

	public final NamesContext names() throws RecognitionException {
		NamesContext _localctx = new NamesContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_names);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			name();
			setState(440);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(436);
				match(T__2);
				setState(437);
				name();
				}
				}
				setState(442);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumDeclContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public NamesContext names() {
			return getRuleContext(NamesContext.class,0);
		}
		public EnumDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterEnumDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitEnumDecl(this);
		}
	}

	public final EnumDeclContext enumDecl() throws RecognitionException {
		EnumDeclContext _localctx = new EnumDeclContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_enumDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(443);
			match(T__29);
			setState(444);
			name();
			setState(445);
			match(T__8);
			setState(446);
			names();
			setState(447);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultContext extends ParserRuleContext {
		public MultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mult; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterMult(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitMult(this);
		}
	}

	public final MultContext mult() throws RecognitionException {
		MultContext _localctx = new MultContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_mult);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__30) | (1L << T__31) | (1L << T__32))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnionContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public UnionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_union; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterUnion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitUnion(this);
		}
	}

	public final UnionContext union() throws RecognitionException {
		UnionContext _localctx = new UnionContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_union);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(451);
			name();
			setState(456);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(452);
					match(T__33);
					setState(453);
					name();
					}
					} 
				}
				setState(458);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuperSetContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public UnionContext union() {
			return getRuleContext(UnionContext.class,0);
		}
		public SuperSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterSuperSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitSuperSet(this);
		}
	}

	public final SuperSetContext superSet() throws RecognitionException {
		SuperSetContext _localctx = new SuperSetContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_superSet);
		try {
			setState(461);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(459);
				name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(460);
				union();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SigExtContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public SuperSetContext superSet() {
			return getRuleContext(SuperSetContext.class,0);
		}
		public SigExtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sigExt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterSigExt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitSigExt(this);
		}
	}

	public final SigExtContext sigExt() throws RecognitionException {
		SigExtContext _localctx = new SigExtContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_sigExt);
		try {
			setState(467);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__34:
				enterOuterAlt(_localctx, 1);
				{
				setState(463);
				match(T__34);
				setState(464);
				name();
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 2);
				{
				setState(465);
				match(T__35);
				setState(466);
				superSet();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SigExtensionContext extends ParserRuleContext {
		public SigExtContext sigExt() {
			return getRuleContext(SigExtContext.class,0);
		}
		public SigExtensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sigExtension; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterSigExtension(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitSigExtension(this);
		}
	}

	public final SigExtensionContext sigExtension() throws RecognitionException {
		SigExtensionContext _localctx = new SigExtensionContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_sigExtension);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(470);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__34 || _la==T__35) {
				{
				setState(469);
				sigExt();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterExprs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitExprs(this);
		}
	}

	public final ExprsContext exprs() throws RecognitionException {
		ExprsContext _localctx = new ExprsContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_exprs);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(480);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(472);
				expr(0);
				setState(477);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(473);
						match(T__2);
						setState(474);
						expr(0);
						}
						} 
					}
					setState(479);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NotOpContext extends ParserRuleContext {
		public NotOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterNotOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitNotOp(this);
		}
	}

	public final NotOpContext notOp() throws RecognitionException {
		NotOpContext _localctx = new NotOpContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_notOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__36 || _la==T__37) {
				{
				setState(482);
				_la = _input.LA(1);
				if ( !(_la==T__36 || _la==T__37) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Decls_eContext extends ParserRuleContext {
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public Decls_eContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decls_e; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterDecls_e(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitDecls_e(this);
		}
	}

	public final Decls_eContext decls_e() throws RecognitionException {
		Decls_eContext _localctx = new Decls_eContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_decls_e);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(485);
			decl();
			setState(490);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(486);
				match(T__2);
				setState(487);
				decl();
				}
				}
				setState(492);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<LetDeclContext> letDecl() {
			return getRuleContexts(LetDeclContext.class);
		}
		public LetDeclContext letDecl(int i) {
			return getRuleContext(LetDeclContext.class,i);
		}
		public BlockOrBarContext blockOrBar() {
			return getRuleContext(BlockOrBarContext.class,0);
		}
		public QuantContext quant() {
			return getRuleContext(QuantContext.class,0);
		}
		public Decls_eContext decls_e() {
			return getRuleContext(Decls_eContext.class,0);
		}
		public UnOpContext unOp() {
			return getRuleContext(UnOpContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprsContext exprs() {
			return getRuleContext(ExprsContext.class,0);
		}
		public DisjFuncContext disjFunc() {
			return getRuleContext(DisjFuncContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BinOpContext binOp() {
			return getRuleContext(BinOpContext.class,0);
		}
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public Decls_fContext decls_f() {
			return getRuleContext(Decls_fContext.class,0);
		}
		public TerminalNode STRING() { return getToken(ALLOYParser.STRING, 0); }
		public ArrowOpContext arrowOp() {
			return getRuleContext(ArrowOpContext.class,0);
		}
		public NotOpContext notOp() {
			return getRuleContext(NotOpContext.class,0);
		}
		public CompareOpContext compareOp() {
			return getRuleContext(CompareOpContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 94;
		enterRecursionRule(_localctx, 94, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(552);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				{
				setState(494);
				match(T__10);
				setState(495);
				letDecl();
				setState(500);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(496);
					match(T__2);
					setState(497);
					letDecl();
					}
					}
					setState(502);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(503);
				blockOrBar();
				}
				break;
			case 2:
				{
				setState(505);
				quant();
				setState(506);
				decls_e();
				setState(507);
				blockOrBar();
				}
				break;
			case 3:
				{
				setState(509);
				unOp();
				setState(510);
				expr(20);
				}
				break;
			case 4:
				{
				setState(512);
				match(T__41);
				setState(514);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
				case 1:
					{
					setState(513);
					match(T__0);
					}
					break;
				}
				setState(516);
				exprs();
				setState(518);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					{
					setState(517);
					match(T__3);
					}
					break;
				}
				}
				break;
			case 5:
				{
				setState(520);
				disjFunc();
				}
				break;
			case 6:
				{
				setState(521);
				constant();
				}
				break;
			case 7:
				{
				setState(522);
				match(T__26);
				}
				break;
			case 8:
				{
				setState(523);
				match(T__42);
				}
				break;
			case 9:
				{
				setState(524);
				match(T__18);
				setState(525);
				expr(0);
				setState(526);
				match(T__19);
				}
				break;
			case 10:
				{
				setState(528);
				name();
				}
				break;
			case 11:
				{
				setState(529);
				match(T__43);
				setState(530);
				name();
				}
				break;
			case 12:
				{
				setState(531);
				match(T__44);
				}
				break;
			case 13:
				{
				setState(532);
				block();
				}
				break;
			case 14:
				{
				setState(533);
				quant();
				setState(534);
				expr(0);
				setState(535);
				binOp();
				setState(536);
				expr(4);
				}
				break;
			case 15:
				{
				setState(538);
				match(T__8);
				setState(539);
				decl();
				setState(544);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(540);
					match(T__2);
					setState(541);
					decl();
					}
					}
					setState(546);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(547);
				blockOrBar();
				setState(548);
				match(T__9);
				}
				break;
			case 16:
				{
				setState(550);
				decls_f();
				}
				break;
			case 17:
				{
				setState(551);
				match(STRING);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(580);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(578);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(554);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(555);
						binOp();
						setState(556);
						expr(20);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(558);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(559);
						arrowOp();
						setState(560);
						expr(19);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(562);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(563);
						notOp();
						setState(564);
						compareOp();
						setState(565);
						expr(18);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(567);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(568);
						_la = _input.LA(1);
						if ( !(_la==T__38 || _la==T__39) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(569);
						expr(0);
						setState(570);
						match(T__40);
						setState(571);
						expr(17);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(573);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(574);
						match(T__0);
						setState(575);
						exprs();
						setState(576);
						match(T__3);
						}
						break;
					}
					} 
				}
				setState(582);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NumContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public NumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_num; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterNum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitNum(this);
		}
	}

	public final NumContext num() throws RecognitionException {
		NumContext _localctx = new NumContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_num);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(584);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__45) {
				{
				setState(583);
				match(T__45);
				}
			}

			setState(586);
			number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Const_setsContext extends ParserRuleContext {
		public Const_setsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_const_sets; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterConst_sets(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitConst_sets(this);
		}
	}

	public final Const_setsContext const_sets() throws RecognitionException {
		Const_setsContext _localctx = new Const_setsContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_const_sets);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(588);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__46) | (1L << T__47) | (1L << T__48))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public NumContext num() {
			return getRuleContext(NumContext.class,0);
		}
		public Const_setsContext const_sets() {
			return getRuleContext(Const_setsContext.class,0);
		}
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitConstant(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_constant);
		try {
			setState(592);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__45:
			case DIGIT:
				enterOuterAlt(_localctx, 1);
				{
				setState(590);
				num();
				}
				break;
			case T__46:
			case T__47:
			case T__48:
				enterOuterAlt(_localctx, 2);
				{
				setState(591);
				const_sets();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DisjFuncContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public DisjFuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjFunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterDisjFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitDisjFunc(this);
		}
	}

	public final DisjFuncContext disjFunc() throws RecognitionException {
		DisjFuncContext _localctx = new DisjFuncContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_disjFunc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(594);
			match(T__49);
			setState(595);
			match(T__0);
			setState(604);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__10) | (1L << T__18) | (1L << T__25) | (1L << T__26) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__36) | (1L << T__37) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57))) != 0) || ((((_la - 79)) & ~0x3f) == 0 && ((1L << (_la - 79)) & ((1L << (T__78 - 79)) | (1L << (DIGIT - 79)) | (1L << (ID - 79)) | (1L << (STRING - 79)))) != 0)) {
				{
				setState(596);
				expr(0);
				setState(601);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(597);
					match(T__2);
					setState(598);
					expr(0);
					}
					}
					setState(603);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(606);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DisjointContext extends ParserRuleContext {
		public DisjointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjoint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterDisjoint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitDisjoint(this);
		}
	}

	public final DisjointContext disjoint() throws RecognitionException {
		DisjointContext _localctx = new DisjointContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_disjoint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(609);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__49 || _la==T__50) {
				{
				setState(608);
				_la = _input.LA(1);
				if ( !(_la==T__49 || _la==T__50) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DisjContext extends ParserRuleContext {
		public DisjContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disj; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterDisj(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitDisj(this);
		}
	}

	public final DisjContext disj() throws RecognitionException {
		DisjContext _localctx = new DisjContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_disj);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(612);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
			case 1:
				{
				setState(611);
				match(T__49);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Comma_optContext extends ParserRuleContext {
		public Comma_optContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comma_opt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterComma_opt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitComma_opt(this);
		}
	}

	public final Comma_optContext comma_opt() throws RecognitionException {
		Comma_optContext _localctx = new Comma_optContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_comma_opt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(615);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				{
				setState(614);
				match(T__2);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public PrivContext priv() {
			return getRuleContext(PrivContext.class,0);
		}
		public DisjointContext disjoint() {
			return getRuleContext(DisjointContext.class,0);
		}
		public NamesContext names() {
			return getRuleContext(NamesContext.class,0);
		}
		public DisjContext disj() {
			return getRuleContext(DisjContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Comma_optContext comma_opt() {
			return getRuleContext(Comma_optContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_decl);
		try {
			setState(629);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(617);
				priv();
				setState(618);
				disjoint();
				setState(619);
				names();
				setState(620);
				match(T__15);
				setState(621);
				disj();
				setState(622);
				expr(0);
				setState(623);
				comma_opt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(625);
				name();
				setState(626);
				match(T__7);
				setState(627);
				expr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetDeclContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public LetDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterLetDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitLetDecl(this);
		}
	}

	public final LetDeclContext letDecl() throws RecognitionException {
		LetDeclContext _localctx = new LetDeclContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_letDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(631);
			name();
			setState(632);
			match(T__7);
			setState(633);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuantContext extends ParserRuleContext {
		public QuantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterQuant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitQuant(this);
		}
	}

	public final QuantContext quant() throws RecognitionException {
		QuantContext _localctx = new QuantContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_quant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(635);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__41) | (1L << T__51) | (1L << T__52))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetCardContext extends ParserRuleContext {
		public SetCardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setCard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterSetCard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitSetCard(this);
		}
	}

	public final SetCardContext setCard() throws RecognitionException {
		SetCardContext _localctx = new SetCardContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_setCard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(637);
			match(T__53);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TcOpContext extends ParserRuleContext {
		public TcOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tcOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterTcOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitTcOp(this);
		}
	}

	public final TcOpContext tcOp() throws RecognitionException {
		TcOpContext _localctx = new TcOpContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_tcOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(639);
			_la = _input.LA(1);
			if ( !(_la==T__54 || _la==T__55) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnOpContext extends ParserRuleContext {
		public MultContext mult() {
			return getRuleContext(MultContext.class,0);
		}
		public SetCardContext setCard() {
			return getRuleContext(SetCardContext.class,0);
		}
		public TcOpContext tcOp() {
			return getRuleContext(TcOpContext.class,0);
		}
		public UnOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterUnOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitUnOp(this);
		}
	}

	public final UnOpContext unOp() throws RecognitionException {
		UnOpContext _localctx = new UnOpContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_unOp);
		try {
			setState(650);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__36:
				enterOuterAlt(_localctx, 1);
				{
				setState(641);
				match(T__36);
				}
				break;
			case T__37:
				enterOuterAlt(_localctx, 2);
				{
				setState(642);
				match(T__37);
				}
				break;
			case T__52:
				enterOuterAlt(_localctx, 3);
				{
				setState(643);
				match(T__52);
				}
				break;
			case T__30:
			case T__31:
			case T__32:
				enterOuterAlt(_localctx, 4);
				{
				setState(644);
				mult();
				}
				break;
			case T__56:
				enterOuterAlt(_localctx, 5);
				{
				setState(645);
				match(T__56);
				}
				break;
			case T__53:
				enterOuterAlt(_localctx, 6);
				{
				setState(646);
				setCard();
				}
				break;
			case T__57:
				enterOuterAlt(_localctx, 7);
				{
				setState(647);
				match(T__57);
				}
				break;
			case T__54:
			case T__55:
				enterOuterAlt(_localctx, 8);
				{
				setState(648);
				tcOp();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 9);
				{
				setState(649);
				match(T__25);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bit_shifter_operatorsContext extends ParserRuleContext {
		public Bit_shifter_operatorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bit_shifter_operators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterBit_shifter_operators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitBit_shifter_operators(this);
		}
	}

	public final Bit_shifter_operatorsContext bit_shifter_operators() throws RecognitionException {
		Bit_shifter_operatorsContext _localctx = new Bit_shifter_operatorsContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_bit_shifter_operators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(652);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__58) | (1L << T__59) | (1L << T__60))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DotOpContext extends ParserRuleContext {
		public DotOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dotOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterDotOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitDotOp(this);
		}
	}

	public final DotOpContext dotOp() throws RecognitionException {
		DotOpContext _localctx = new DotOpContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_dotOp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(654);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddContext extends ParserRuleContext {
		public AddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitAdd(this);
		}
	}

	public final AddContext add() throws RecognitionException {
		AddContext _localctx = new AddContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_add);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(656);
			match(T__33);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubContext extends ParserRuleContext {
		public SubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sub; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitSub(this);
		}
	}

	public final SubContext sub() throws RecognitionException {
		SubContext _localctx = new SubContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_sub);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(658);
			match(T__45);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinOpContext extends ParserRuleContext {
		public AddContext add() {
			return getRuleContext(AddContext.class,0);
		}
		public SubContext sub() {
			return getRuleContext(SubContext.class,0);
		}
		public DotOpContext dotOp() {
			return getRuleContext(DotOpContext.class,0);
		}
		public Bit_shifter_operatorsContext bit_shifter_operators() {
			return getRuleContext(Bit_shifter_operatorsContext.class,0);
		}
		public BinOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterBinOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitBinOp(this);
		}
	}

	public final BinOpContext binOp() throws RecognitionException {
		BinOpContext _localctx = new BinOpContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_binOp);
		try {
			setState(676);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__61:
				enterOuterAlt(_localctx, 1);
				{
				setState(660);
				match(T__61);
				}
				break;
			case T__62:
				enterOuterAlt(_localctx, 2);
				{
				setState(661);
				match(T__62);
				}
				break;
			case T__63:
				enterOuterAlt(_localctx, 3);
				{
				setState(662);
				match(T__63);
				}
				break;
			case T__64:
				enterOuterAlt(_localctx, 4);
				{
				setState(663);
				match(T__64);
				}
				break;
			case T__65:
				enterOuterAlt(_localctx, 5);
				{
				setState(664);
				match(T__65);
				}
				break;
			case T__66:
				enterOuterAlt(_localctx, 6);
				{
				setState(665);
				match(T__66);
				}
				break;
			case T__38:
				enterOuterAlt(_localctx, 7);
				{
				setState(666);
				match(T__38);
				}
				break;
			case T__39:
				enterOuterAlt(_localctx, 8);
				{
				setState(667);
				match(T__39);
				}
				break;
			case T__67:
				enterOuterAlt(_localctx, 9);
				{
				setState(668);
				match(T__67);
				}
				break;
			case T__33:
				enterOuterAlt(_localctx, 10);
				{
				setState(669);
				add();
				}
				break;
			case T__45:
				enterOuterAlt(_localctx, 11);
				{
				setState(670);
				sub();
				}
				break;
			case T__68:
				enterOuterAlt(_localctx, 12);
				{
				setState(671);
				match(T__68);
				}
				break;
			case T__69:
				enterOuterAlt(_localctx, 13);
				{
				setState(672);
				match(T__69);
				}
				break;
			case T__70:
				enterOuterAlt(_localctx, 14);
				{
				setState(673);
				match(T__70);
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 15);
				{
				setState(674);
				dotOp();
				}
				break;
			case T__58:
			case T__59:
			case T__60:
				enterOuterAlt(_localctx, 16);
				{
				setState(675);
				bit_shifter_operators();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mult_or_setContext extends ParserRuleContext {
		public MultContext mult() {
			return getRuleContext(MultContext.class,0);
		}
		public Mult_or_setContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mult_or_set; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterMult_or_set(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitMult_or_set(this);
		}
	}

	public final Mult_or_setContext mult_or_set() throws RecognitionException {
		Mult_or_setContext _localctx = new Mult_or_setContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_mult_or_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(680);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				{
				setState(678);
				mult();
				}
				break;
			case 2:
				{
				setState(679);
				match(T__56);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrowOpContext extends ParserRuleContext {
		public List<Mult_or_setContext> mult_or_set() {
			return getRuleContexts(Mult_or_setContext.class);
		}
		public Mult_or_setContext mult_or_set(int i) {
			return getRuleContext(Mult_or_setContext.class,i);
		}
		public ArrowOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrowOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterArrowOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitArrowOp(this);
		}
	}

	public final ArrowOpContext arrowOp() throws RecognitionException {
		ArrowOpContext _localctx = new ArrowOpContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_arrowOp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(682);
			mult_or_set();
			setState(683);
			match(T__71);
			setState(684);
			mult_or_set();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rel_operatorsContext extends ParserRuleContext {
		public Rel_operatorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rel_operators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterRel_operators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitRel_operators(this);
		}
	}

	public final Rel_operatorsContext rel_operators() throws RecognitionException {
		Rel_operatorsContext _localctx = new Rel_operatorsContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_rel_operators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(686);
			_la = _input.LA(1);
			if ( !(_la==T__7 || ((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & ((1L << (T__72 - 73)) | (1L << (T__73 - 73)) | (1L << (T__74 - 73)) | (1L << (T__75 - 73)) | (1L << (T__76 - 73)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompareOpContext extends ParserRuleContext {
		public Rel_operatorsContext rel_operators() {
			return getRuleContext(Rel_operatorsContext.class,0);
		}
		public CompareOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compareOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterCompareOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitCompareOp(this);
		}
	}

	public final CompareOpContext compareOp() throws RecognitionException {
		CompareOpContext _localctx = new CompareOpContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_compareOp);
		try {
			setState(690);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
			case T__72:
			case T__73:
			case T__74:
			case T__75:
			case T__76:
				enterOuterAlt(_localctx, 1);
				{
				setState(688);
				rel_operators();
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 2);
				{
				setState(689);
				match(T__35);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(692);
			match(T__8);
			setState(696);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__10) | (1L << T__18) | (1L << T__25) | (1L << T__26) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__36) | (1L << T__37) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57))) != 0) || ((((_la - 79)) & ~0x3f) == 0 && ((1L << (_la - 79)) & ((1L << (T__78 - 79)) | (1L << (DIGIT - 79)) | (1L << (ID - 79)) | (1L << (STRING - 79)))) != 0)) {
				{
				{
				setState(693);
				expr(0);
				}
				}
				setState(698);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(699);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Block_optContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Block_optContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_opt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterBlock_opt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitBlock_opt(this);
		}
	}

	public final Block_optContext block_opt() throws RecognitionException {
		Block_optContext _localctx = new Block_optContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_block_opt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(702);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(701);
				block();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockOrBarContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BarContext bar() {
			return getRuleContext(BarContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BlockOrBarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockOrBar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterBlockOrBar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitBlockOrBar(this);
		}
	}

	public final BlockOrBarContext blockOrBar() throws RecognitionException {
		BlockOrBarContext _localctx = new BlockOrBarContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_blockOrBar);
		try {
			setState(708);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(704);
				block();
				}
				break;
			case T__77:
				enterOuterAlt(_localctx, 2);
				{
				setState(705);
				bar();
				setState(706);
				expr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BarContext extends ParserRuleContext {
		public BarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterBar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitBar(this);
		}
	}

	public final BarContext bar() throws RecognitionException {
		BarContext _localctx = new BarContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_bar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(710);
			match(T__77);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(ALLOYParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ALLOYParser.ID, i);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_name);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(713);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__78) {
				{
				setState(712);
				match(T__78);
				}
			}

			setState(719);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(715);
					match(ID);
					setState(716);
					match(T__79);
					}
					} 
				}
				setState(721);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			}
			setState(722);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public List<TerminalNode> DIGIT() { return getTokens(ALLOYParser.DIGIT); }
		public TerminalNode DIGIT(int i) {
			return getToken(ALLOYParser.DIGIT, i);
		}
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ALLOYListener ) ((ALLOYListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_number);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(725); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(724);
					match(DIGIT);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(727); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,80,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 47:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 19);
		case 1:
			return precpred(_ctx, 18);
		case 2:
			return precpred(_ctx, 17);
		case 3:
			return precpred(_ctx, 16);
		case 4:
			return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3Y\u02dc\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\3\2\5\2\u009c\n\2\3\2\7\2\u009f\n\2\f\2\16"+
		"\2\u00a2\13\2\3\2\5\2\u00a5\n\2\3\2\7\2\u00a8\n\2\f\2\16\2\u00ab\13\2"+
		"\3\2\3\2\5\2\u00af\n\2\3\3\3\3\5\3\u00b3\n\3\3\3\3\3\3\3\5\3\u00b8\n\3"+
		"\3\3\7\3\u00bb\n\3\f\3\16\3\u00be\13\3\3\3\3\3\5\3\u00c2\n\3\3\4\3\4\3"+
		"\4\3\4\3\5\5\5\u00c9\n\5\3\6\3\6\5\6\u00cd\n\6\3\7\3\7\3\7\3\7\5\7\u00d3"+
		"\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\5\t\u00dc\n\t\3\t\3\t\7\t\u00e0\n\t\f"+
		"\t\16\t\u00e3\13\t\3\t\5\t\u00e6\n\t\3\t\3\t\3\t\3\t\5\t\u00ec\n\t\5\t"+
		"\u00ee\n\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00f6\n\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\5\13\u0102\n\13\3\f\3\f\5\f\u0106\n\f\3\f\3"+
		"\f\3\r\5\r\u010b\n\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\5\17"+
		"\u0116\n\17\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\5\23\u012a\n\23\3\23\3\23\3\23\5\23\u012f"+
		"\n\23\5\23\u0131\n\23\3\24\3\24\3\24\5\24\u0136\n\24\3\25\5\25\u0139\n"+
		"\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\7\30\u0146"+
		"\n\30\f\30\16\30\u0149\13\30\5\30\u014b\n\30\3\30\3\30\3\30\3\30\3\30"+
		"\7\30\u0152\n\30\f\30\16\30\u0155\13\30\5\30\u0157\n\30\3\30\5\30\u015a"+
		"\n\30\3\31\5\31\u015d\n\31\3\32\5\32\u0160\n\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\33\3\33\3\33\7\33\u016a\n\33\f\33\16\33\u016d\13\33\3\34\3\34\5"+
		"\34\u0171\n\34\3\35\3\35\5\35\u0175\n\35\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\5\36\u0182\n\36\3\37\5\37\u0185\n\37\3 \3 \3"+
		" \3 \3 \5 \u018c\n \3!\5!\u018f\n!\3!\3!\3!\7!\u0194\n!\f!\16!\u0197\13"+
		"!\5!\u0199\n!\3\"\5\"\u019c\n\"\3#\5#\u019f\n#\3$\5$\u01a2\n$\3%\3%\3"+
		"%\3%\3%\3%\5%\u01aa\n%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\7\'\u01b9"+
		"\n\'\f\'\16\'\u01bc\13\'\3(\3(\3(\3(\3(\3(\3)\3)\3*\3*\3*\7*\u01c9\n*"+
		"\f*\16*\u01cc\13*\3+\3+\5+\u01d0\n+\3,\3,\3,\3,\5,\u01d6\n,\3-\5-\u01d9"+
		"\n-\3.\3.\3.\7.\u01de\n.\f.\16.\u01e1\13.\5.\u01e3\n.\3/\5/\u01e6\n/\3"+
		"\60\3\60\3\60\7\60\u01eb\n\60\f\60\16\60\u01ee\13\60\3\61\3\61\3\61\3"+
		"\61\3\61\7\61\u01f5\n\61\f\61\16\61\u01f8\13\61\3\61\3\61\3\61\3\61\3"+
		"\61\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u0205\n\61\3\61\3\61\5\61\u0209"+
		"\n\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61"+
		"\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\7\61\u0221\n\61\f\61\16"+
		"\61\u0224\13\61\3\61\3\61\3\61\3\61\3\61\5\61\u022b\n\61\3\61\3\61\3\61"+
		"\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61"+
		"\3\61\3\61\3\61\3\61\3\61\3\61\3\61\7\61\u0245\n\61\f\61\16\61\u0248\13"+
		"\61\3\62\5\62\u024b\n\62\3\62\3\62\3\63\3\63\3\64\3\64\5\64\u0253\n\64"+
		"\3\65\3\65\3\65\3\65\3\65\7\65\u025a\n\65\f\65\16\65\u025d\13\65\5\65"+
		"\u025f\n\65\3\65\3\65\3\66\5\66\u0264\n\66\3\67\5\67\u0267\n\67\38\58"+
		"\u026a\n8\39\39\39\39\39\39\39\39\39\39\39\39\59\u0278\n9\3:\3:\3:\3:"+
		"\3;\3;\3<\3<\3=\3=\3>\3>\3>\3>\3>\3>\3>\3>\3>\5>\u028d\n>\3?\3?\3@\3@"+
		"\3A\3A\3B\3B\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\5C\u02a7"+
		"\nC\3D\3D\5D\u02ab\nD\3E\3E\3E\3E\3F\3F\3G\3G\5G\u02b5\nG\3H\3H\7H\u02b9"+
		"\nH\fH\16H\u02bc\13H\3H\3H\3I\5I\u02c1\nI\3J\3J\3J\3J\5J\u02c7\nJ\3K\3"+
		"K\3L\5L\u02cc\nL\3L\3L\7L\u02d0\nL\fL\16L\u02d3\13L\3L\3L\3M\6M\u02d8"+
		"\nM\rM\16M\u02d9\3M\3\u01ca\3`N\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082"+
		"\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\2\f"+
		"\3\2\23\24\3\2!#\3\2\'(\3\2)*\3\2\61\63\3\2\64\65\5\2!#,,\66\67\3\29:"+
		"\3\2=?\4\2\n\nKO\2\u0311\2\u00ae\3\2\2\2\4\u00c1\3\2\2\2\6\u00c3\3\2\2"+
		"\2\b\u00c8\3\2\2\2\n\u00cc\3\2\2\2\f\u00d2\3\2\2\2\16\u00d4\3\2\2\2\20"+
		"\u00ed\3\2\2\2\22\u00ef\3\2\2\2\24\u0101\3\2\2\2\26\u0103\3\2\2\2\30\u010a"+
		"\3\2\2\2\32\u010c\3\2\2\2\34\u0115\3\2\2\2\36\u0117\3\2\2\2 \u011b\3\2"+
		"\2\2\"\u011d\3\2\2\2$\u0130\3\2\2\2&\u0135\3\2\2\2(\u0138\3\2\2\2*\u013a"+
		"\3\2\2\2,\u013c\3\2\2\2.\u0159\3\2\2\2\60\u015c\3\2\2\2\62\u015f\3\2\2"+
		"\2\64\u0166\3\2\2\2\66\u0170\3\2\2\28\u0174\3\2\2\2:\u0181\3\2\2\2<\u0184"+
		"\3\2\2\2>\u0186\3\2\2\2@\u0198\3\2\2\2B\u019b\3\2\2\2D\u019e\3\2\2\2F"+
		"\u01a1\3\2\2\2H\u01a9\3\2\2\2J\u01ab\3\2\2\2L\u01b5\3\2\2\2N\u01bd\3\2"+
		"\2\2P\u01c3\3\2\2\2R\u01c5\3\2\2\2T\u01cf\3\2\2\2V\u01d5\3\2\2\2X\u01d8"+
		"\3\2\2\2Z\u01e2\3\2\2\2\\\u01e5\3\2\2\2^\u01e7\3\2\2\2`\u022a\3\2\2\2"+
		"b\u024a\3\2\2\2d\u024e\3\2\2\2f\u0252\3\2\2\2h\u0254\3\2\2\2j\u0263\3"+
		"\2\2\2l\u0266\3\2\2\2n\u0269\3\2\2\2p\u0277\3\2\2\2r\u0279\3\2\2\2t\u027d"+
		"\3\2\2\2v\u027f\3\2\2\2x\u0281\3\2\2\2z\u028c\3\2\2\2|\u028e\3\2\2\2~"+
		"\u0290\3\2\2\2\u0080\u0292\3\2\2\2\u0082\u0294\3\2\2\2\u0084\u02a6\3\2"+
		"\2\2\u0086\u02aa\3\2\2\2\u0088\u02ac\3\2\2\2\u008a\u02b0\3\2\2\2\u008c"+
		"\u02b4\3\2\2\2\u008e\u02b6\3\2\2\2\u0090\u02c0\3\2\2\2\u0092\u02c6\3\2"+
		"\2\2\u0094\u02c8\3\2\2\2\u0096\u02cb\3\2\2\2\u0098\u02d7\3\2\2\2\u009a"+
		"\u009c\5\6\4\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u00a0\3\2"+
		"\2\2\u009d\u009f\5\16\b\2\u009e\u009d\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0"+
		"\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2"+
		"\2\2\u00a3\u00a5\5\6\4\2\u00a4\u00a3\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
		"\u00a9\3\2\2\2\u00a6\u00a8\5\24\13\2\u00a7\u00a6\3\2\2\2\u00a8\u00ab\3"+
		"\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab"+
		"\u00a9\3\2\2\2\u00ac\u00af\7\2\2\3\u00ad\u00af\7\2\2\3\u00ae\u009b\3\2"+
		"\2\2\u00ae\u00ad\3\2\2\2\u00af\3\3\2\2\2\u00b0\u00b2\7\3\2\2\u00b1\u00b3"+
		"\7\4\2\2\u00b2\u00b1\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00bc\5\u0096L\2\u00b5\u00b7\7\5\2\2\u00b6\u00b8\7\4\2\2\u00b7\u00b6"+
		"\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bb\5\u0096L"+
		"\2\u00ba\u00b5\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd"+
		"\3\2\2\2\u00bd\u00bf\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c0\7\6\2\2\u00c0"+
		"\u00c2\3\2\2\2\u00c1\u00b0\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\5\3\2\2\2"+
		"\u00c3\u00c4\7\7\2\2\u00c4\u00c5\5\u0096L\2\u00c5\u00c6\5\4\3\2\u00c6"+
		"\7\3\2\2\2\u00c7\u00c9\5L\'\2\u00c8\u00c7\3\2\2\2\u00c8\u00c9\3\2\2\2"+
		"\u00c9\t\3\2\2\2\u00ca\u00cb\7\b\2\2\u00cb\u00cd\5\u0096L\2\u00cc\u00ca"+
		"\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\13\3\2\2\2\u00ce\u00cf\7\3\2\2\u00cf"+
		"\u00d0\5\b\5\2\u00d0\u00d1\7\6\2\2\u00d1\u00d3\3\2\2\2\u00d2\u00ce\3\2"+
		"\2\2\u00d2\u00d3\3\2\2\2\u00d3\r\3\2\2\2\u00d4\u00d5\5F$\2\u00d5\u00d6"+
		"\7\t\2\2\u00d6\u00d7\5\u0096L\2\u00d7\u00d8\5\f\7\2\u00d8\u00d9\5\n\6"+
		"\2\u00d9\17\3\2\2\2\u00da\u00dc\7\n\2\2\u00db\u00da\3\2\2\2\u00db\u00dc"+
		"\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00e5\7\13\2\2\u00de\u00e0\5`\61\2"+
		"\u00df\u00de\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2"+
		"\3\2\2\2\u00e2\u00e6\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e6\5@!\2\u00e5"+
		"\u00e1\3\2\2\2\u00e5\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00ee\7\f"+
		"\2\2\u00e8\u00eb\7\n\2\2\u00e9\u00ec\5`\61\2\u00ea\u00ec\5@!\2\u00eb\u00e9"+
		"\3\2\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ee\3\2\2\2\u00ed\u00db\3\2\2\2\u00ed"+
		"\u00e8\3\2\2\2\u00ee\21\3\2\2\2\u00ef\u00f0\7\r\2\2\u00f0\u00f5\5\u0096"+
		"L\2\u00f1\u00f2\7\3\2\2\u00f2\u00f3\5L\'\2\u00f3\u00f4\7\6\2\2\u00f4\u00f6"+
		"\3\2\2\2\u00f5\u00f1\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7"+
		"\u00f8\5\20\t\2\u00f8\23\3\2\2\2\u00f9\u0102\5\26\f\2\u00fa\u0102\5\32"+
		"\16\2\u00fb\u0102\5\"\22\2\u00fc\u0102\5,\27\2\u00fd\u0102\5N(\2\u00fe"+
		"\u0102\5J&\2\u00ff\u0102\5\62\32\2\u0100\u0102\5\22\n\2\u0101\u00f9\3"+
		"\2\2\2\u0101\u00fa\3\2\2\2\u0101\u00fb\3\2\2\2\u0101\u00fc\3\2\2\2\u0101"+
		"\u00fd\3\2\2\2\u0101\u00fe\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0100\3\2"+
		"\2\2\u0102\25\3\2\2\2\u0103\u0105\7\16\2\2\u0104\u0106\5\u0096L\2\u0105"+
		"\u0104\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108\5\u008e"+
		"H\2\u0108\27\3\2\2\2\u0109\u010b\5\u0096L\2\u010a\u0109\3\2\2\2\u010a"+
		"\u010b\3\2\2\2\u010b\31\3\2\2\2\u010c\u010d\7\17\2\2\u010d\u010e\5\30"+
		"\r\2\u010e\u010f\5\u008eH\2\u010f\33\3\2\2\2\u0110\u0111\5\u0096L\2\u0111"+
		"\u0112\7\20\2\2\u0112\u0113\7T\2\2\u0113\u0116\3\2\2\2\u0114\u0116\7T"+
		"\2\2\u0115\u0110\3\2\2\2\u0115\u0114\3\2\2\2\u0116\35\3\2\2\2\u0117\u0118"+
		"\7\13\2\2\u0118\u0119\5@!\2\u0119\u011a\7\f\2\2\u011a\37\3\2\2\2\u011b"+
		"\u011c\5`\61\2\u011c!\3\2\2\2\u011d\u011e\5F$\2\u011e\u011f\7\21\2\2\u011f"+
		"\u0120\5\34\17\2\u0120\u0121\5\60\31\2\u0121\u0122\7\22\2\2\u0122\u0123"+
		"\5`\61\2\u0123\u0124\7\13\2\2\u0124\u0125\5 \21\2\u0125\u0126\7\f\2\2"+
		"\u0126#\3\2\2\2\u0127\u012a\5\u0096L\2\u0128\u012a\5\u008eH\2\u0129\u0127"+
		"\3\2\2\2\u0129\u0128\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u0131\3\2\2\2\u012b"+
		"\u012c\5\34\17\2\u012c\u012d\5\u008eH\2\u012d\u012f\3\2\2\2\u012e\u012b"+
		"\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0131\3\2\2\2\u0130\u0129\3\2\2\2\u0130"+
		"\u012e\3\2\2\2\u0131%\3\2\2\2\u0132\u0133\5\u0096L\2\u0133\u0134\7\22"+
		"\2\2\u0134\u0136\3\2\2\2\u0135\u0132\3\2\2\2\u0135\u0136\3\2\2\2\u0136"+
		"\'\3\2\2\2\u0137\u0139\5:\36\2\u0138\u0137\3\2\2\2\u0138\u0139\3\2\2\2"+
		"\u0139)\3\2\2\2\u013a\u013b\t\2\2\2\u013b+\3\2\2\2\u013c\u013d\5&\24\2"+
		"\u013d\u013e\5*\26\2\u013e\u013f\5$\23\2\u013f\u0140\5(\25\2\u0140-\3"+
		"\2\2\2\u0141\u014a\7\25\2\2\u0142\u0147\5p9\2\u0143\u0144\7\5\2\2\u0144"+
		"\u0146\5p9\2\u0145\u0143\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0145\3\2\2"+
		"\2\u0147\u0148\3\2\2\2\u0148\u014b\3\2\2\2\u0149\u0147\3\2\2\2\u014a\u0142"+
		"\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u015a\7\26\2\2"+
		"\u014d\u0156\7\3\2\2\u014e\u0153\5p9\2\u014f\u0150\7\5\2\2\u0150\u0152"+
		"\5p9\2\u0151\u014f\3\2\2\2\u0152\u0155\3\2\2\2\u0153\u0151\3\2\2\2\u0153"+
		"\u0154\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0156\u014e\3\2"+
		"\2\2\u0156\u0157\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u015a\7\6\2\2\u0159"+
		"\u0141\3\2\2\2\u0159\u014d\3\2\2\2\u015a/\3\2\2\2\u015b\u015d\5.\30\2"+
		"\u015c\u015b\3\2\2\2\u015c\u015d\3\2\2\2\u015d\61\3\2\2\2\u015e\u0160"+
		"\7\27\2\2\u015f\u015e\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0161\3\2\2\2"+
		"\u0161\u0162\7\30\2\2\u0162\u0163\5\34\17\2\u0163\u0164\5\60\31\2\u0164"+
		"\u0165\5\u008eH\2\u0165\63\3\2\2\2\u0166\u016b\5> \2\u0167\u0168\7\5\2"+
		"\2\u0168\u016a\5> \2\u0169\u0167\3\2\2\2\u016a\u016d\3\2\2\2\u016b\u0169"+
		"\3\2\2\2\u016b\u016c\3\2\2\2\u016c\65\3\2\2\2\u016d\u016b\3\2\2\2\u016e"+
		"\u016f\7\31\2\2\u016f\u0171\5\64\33\2\u0170\u016e\3\2\2\2\u0170\u0171"+
		"\3\2\2\2\u0171\67\3\2\2\2\u0172\u0173\7\32\2\2\u0173\u0175\7S\2\2\u0174"+
		"\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u01759\3\2\2\2\u0176\u0177\7\33\2\2"+
		"\u0177\u0178\5\u0098M\2\u0178\u0179\5\66\34\2\u0179\u017a\58\35\2\u017a"+
		"\u0182\3\2\2\2\u017b\u017c\7\33\2\2\u017c\u017d\5\64\33\2\u017d\u017e"+
		"\58\35\2\u017e\u0182\3\2\2\2\u017f\u0180\7\32\2\2\u0180\u0182\7S\2\2\u0181"+
		"\u0176\3\2\2\2\u0181\u017b\3\2\2\2\u0181\u017f\3\2\2\2\u0182;\3\2\2\2"+
		"\u0183\u0185\7\4\2\2\u0184\u0183\3\2\2\2\u0184\u0185\3\2\2\2\u0185=\3"+
		"\2\2\2\u0186\u0187\5<\37\2\u0187\u018b\5\u0098M\2\u0188\u018c\5\u0096"+
		"L\2\u0189\u018c\7\34\2\2\u018a\u018c\7\35\2\2\u018b\u0188\3\2\2\2\u018b"+
		"\u0189\3\2\2\2\u018b\u018a\3\2\2\2\u018c?\3\2\2\2\u018d\u018f\7\5\2\2"+
		"\u018e\u018d\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u0190\3\2\2\2\u0190\u0195"+
		"\5p9\2\u0191\u0192\7\5\2\2\u0192\u0194\5p9\2\u0193\u0191\3\2\2\2\u0194"+
		"\u0197\3\2\2\2\u0195\u0193\3\2\2\2\u0195\u0196\3\2\2\2\u0196\u0199\3\2"+
		"\2\2\u0197\u0195\3\2\2\2\u0198\u018e\3\2\2\2\u0198\u0199\3\2\2\2\u0199"+
		"A\3\2\2\2\u019a\u019c\5P)\2\u019b\u019a\3\2\2\2\u019b\u019c\3\2\2\2\u019c"+
		"C\3\2\2\2\u019d\u019f\7\36\2\2\u019e\u019d\3\2\2\2\u019e\u019f\3\2\2\2"+
		"\u019fE\3\2\2\2\u01a0\u01a2\7\27\2\2\u01a1\u01a0\3\2\2\2\u01a1\u01a2\3"+
		"\2\2\2\u01a2G\3\2\2\2\u01a3\u01a4\5D#\2\u01a4\u01a5\5B\"\2\u01a5\u01aa"+
		"\3\2\2\2\u01a6\u01a7\5B\"\2\u01a7\u01a8\5D#\2\u01a8\u01aa\3\2\2\2\u01a9"+
		"\u01a3\3\2\2\2\u01a9\u01a6\3\2\2\2\u01aaI\3\2\2\2\u01ab\u01ac\5F$\2\u01ac"+
		"\u01ad\5H%\2\u01ad\u01ae\7\37\2\2\u01ae\u01af\5L\'\2\u01af\u01b0\5X-\2"+
		"\u01b0\u01b1\7\13\2\2\u01b1\u01b2\5@!\2\u01b2\u01b3\7\f\2\2\u01b3\u01b4"+
		"\5\u0090I\2\u01b4K\3\2\2\2\u01b5\u01ba\5\u0096L\2\u01b6\u01b7\7\5\2\2"+
		"\u01b7\u01b9\5\u0096L\2\u01b8\u01b6\3\2\2\2\u01b9\u01bc\3\2\2\2\u01ba"+
		"\u01b8\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bbM\3\2\2\2\u01bc\u01ba\3\2\2\2"+
		"\u01bd\u01be\7 \2\2\u01be\u01bf\5\u0096L\2\u01bf\u01c0\7\13\2\2\u01c0"+
		"\u01c1\5L\'\2\u01c1\u01c2\7\f\2\2\u01c2O\3\2\2\2\u01c3\u01c4\t\3\2\2\u01c4"+
		"Q\3\2\2\2\u01c5\u01ca\5\u0096L\2\u01c6\u01c7\7$\2\2\u01c7\u01c9\5\u0096"+
		"L\2\u01c8\u01c6\3\2\2\2\u01c9\u01cc\3\2\2\2\u01ca\u01cb\3\2\2\2\u01ca"+
		"\u01c8\3\2\2\2\u01cbS\3\2\2\2\u01cc\u01ca\3\2\2\2\u01cd\u01d0\5\u0096"+
		"L\2\u01ce\u01d0\5R*\2\u01cf\u01cd\3\2\2\2\u01cf\u01ce\3\2\2\2\u01d0U\3"+
		"\2\2\2\u01d1\u01d2\7%\2\2\u01d2\u01d6\5\u0096L\2\u01d3\u01d4\7&\2\2\u01d4"+
		"\u01d6\5T+\2\u01d5\u01d1\3\2\2\2\u01d5\u01d3\3\2\2\2\u01d6W\3\2\2\2\u01d7"+
		"\u01d9\5V,\2\u01d8\u01d7\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9Y\3\2\2\2\u01da"+
		"\u01df\5`\61\2\u01db\u01dc\7\5\2\2\u01dc\u01de\5`\61\2\u01dd\u01db\3\2"+
		"\2\2\u01de\u01e1\3\2\2\2\u01df\u01dd\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0"+
		"\u01e3\3\2\2\2\u01e1\u01df\3\2\2\2\u01e2\u01da\3\2\2\2\u01e2\u01e3\3\2"+
		"\2\2\u01e3[\3\2\2\2\u01e4\u01e6\t\4\2\2\u01e5\u01e4\3\2\2\2\u01e5\u01e6"+
		"\3\2\2\2\u01e6]\3\2\2\2\u01e7\u01ec\5p9\2\u01e8\u01e9\7\5\2\2\u01e9\u01eb"+
		"\5p9\2\u01ea\u01e8\3\2\2\2\u01eb\u01ee\3\2\2\2\u01ec\u01ea\3\2\2\2\u01ec"+
		"\u01ed\3\2\2\2\u01ed_\3\2\2\2\u01ee\u01ec\3\2\2\2\u01ef\u01f0\b\61\1\2"+
		"\u01f0\u01f1\7\r\2\2\u01f1\u01f6\5r:\2\u01f2\u01f3\7\5\2\2\u01f3\u01f5"+
		"\5r:\2\u01f4\u01f2\3\2\2\2\u01f5\u01f8\3\2\2\2\u01f6\u01f4\3\2\2\2\u01f6"+
		"\u01f7\3\2\2\2\u01f7\u01f9\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f9\u01fa\5\u0092"+
		"J\2\u01fa\u022b\3\2\2\2\u01fb\u01fc\5t;\2\u01fc\u01fd\5^\60\2\u01fd\u01fe"+
		"\5\u0092J\2\u01fe\u022b\3\2\2\2\u01ff\u0200\5z>\2\u0200\u0201\5`\61\26"+
		"\u0201\u022b\3\2\2\2\u0202\u0204\7,\2\2\u0203\u0205\7\3\2\2\u0204\u0203"+
		"\3\2\2\2\u0204\u0205\3\2\2\2\u0205\u0206\3\2\2\2\u0206\u0208\5Z.\2\u0207"+
		"\u0209\7\6\2\2\u0208\u0207\3\2\2\2\u0208\u0209\3\2\2\2\u0209\u022b\3\2"+
		"\2\2\u020a\u022b\5h\65\2\u020b\u022b\5f\64\2\u020c\u022b\7\35\2\2\u020d"+
		"\u022b\7-\2\2\u020e\u020f\7\25\2\2\u020f\u0210\5`\61\2\u0210\u0211\7\26"+
		"\2\2\u0211\u022b\3\2\2\2\u0212\u022b\5\u0096L\2\u0213\u0214\7.\2\2\u0214"+
		"\u022b\5\u0096L\2\u0215\u022b\7/\2\2\u0216\u022b\5\u008eH\2\u0217\u0218"+
		"\5t;\2\u0218\u0219\5`\61\2\u0219\u021a\5\u0084C\2\u021a\u021b\5`\61\6"+
		"\u021b\u022b\3\2\2\2\u021c\u021d\7\13\2\2\u021d\u0222\5p9\2\u021e\u021f"+
		"\7\5\2\2\u021f\u0221\5p9\2\u0220\u021e\3\2\2\2\u0221\u0224\3\2\2\2\u0222"+
		"\u0220\3\2\2\2\u0222\u0223\3\2\2\2\u0223\u0225\3\2\2\2\u0224\u0222\3\2"+
		"\2\2\u0225\u0226\5\u0092J\2\u0226\u0227\7\f\2\2\u0227\u022b\3\2\2\2\u0228"+
		"\u022b\5\36\20\2\u0229\u022b\7Y\2\2\u022a\u01ef\3\2\2\2\u022a\u01fb\3"+
		"\2\2\2\u022a\u01ff\3\2\2\2\u022a\u0202\3\2\2\2\u022a\u020a\3\2\2\2\u022a"+
		"\u020b\3\2\2\2\u022a\u020c\3\2\2\2\u022a\u020d\3\2\2\2\u022a\u020e\3\2"+
		"\2\2\u022a\u0212\3\2\2\2\u022a\u0213\3\2\2\2\u022a\u0215\3\2\2\2\u022a"+
		"\u0216\3\2\2\2\u022a\u0217\3\2\2\2\u022a\u021c\3\2\2\2\u022a\u0228\3\2"+
		"\2\2\u022a\u0229\3\2\2\2\u022b\u0246\3\2\2\2\u022c\u022d\f\25\2\2\u022d"+
		"\u022e\5\u0084C\2\u022e\u022f\5`\61\26\u022f\u0245\3\2\2\2\u0230\u0231"+
		"\f\24\2\2\u0231\u0232\5\u0088E\2\u0232\u0233\5`\61\25\u0233\u0245\3\2"+
		"\2\2\u0234\u0235\f\23\2\2\u0235\u0236\5\\/\2\u0236\u0237\5\u008cG\2\u0237"+
		"\u0238\5`\61\24\u0238\u0245\3\2\2\2\u0239\u023a\f\22\2\2\u023a\u023b\t"+
		"\5\2\2\u023b\u023c\5`\61\2\u023c\u023d\7+\2\2\u023d\u023e\5`\61\23\u023e"+
		"\u0245\3\2\2\2\u023f\u0240\f\20\2\2\u0240\u0241\7\3\2\2\u0241\u0242\5"+
		"Z.\2\u0242\u0243\7\6\2\2\u0243\u0245\3\2\2\2\u0244\u022c\3\2\2\2\u0244"+
		"\u0230\3\2\2\2\u0244\u0234\3\2\2\2\u0244\u0239\3\2\2\2\u0244\u023f\3\2"+
		"\2\2\u0245\u0248\3\2\2\2\u0246\u0244\3\2\2\2\u0246\u0247\3\2\2\2\u0247"+
		"a\3\2\2\2\u0248\u0246\3\2\2\2\u0249\u024b\7\60\2\2\u024a\u0249\3\2\2\2"+
		"\u024a\u024b\3\2\2\2\u024b\u024c\3\2\2\2\u024c\u024d\5\u0098M\2\u024d"+
		"c\3\2\2\2\u024e\u024f\t\6\2\2\u024fe\3\2\2\2\u0250\u0253\5b\62\2\u0251"+
		"\u0253\5d\63\2\u0252\u0250\3\2\2\2\u0252\u0251\3\2\2\2\u0253g\3\2\2\2"+
		"\u0254\u0255\7\64\2\2\u0255\u025e\7\3\2\2\u0256\u025b\5`\61\2\u0257\u0258"+
		"\7\5\2\2\u0258\u025a\5`\61\2\u0259\u0257\3\2\2\2\u025a\u025d\3\2\2\2\u025b"+
		"\u0259\3\2\2\2\u025b\u025c\3\2\2\2\u025c\u025f\3\2\2\2\u025d\u025b\3\2"+
		"\2\2\u025e\u0256\3\2\2\2\u025e\u025f\3\2\2\2\u025f\u0260\3\2\2\2\u0260"+
		"\u0261\7\6\2\2\u0261i\3\2\2\2\u0262\u0264\t\7\2\2\u0263\u0262\3\2\2\2"+
		"\u0263\u0264\3\2\2\2\u0264k\3\2\2\2\u0265\u0267\7\64\2\2\u0266\u0265\3"+
		"\2\2\2\u0266\u0267\3\2\2\2\u0267m\3\2\2\2\u0268\u026a\7\5\2\2\u0269\u0268"+
		"\3\2\2\2\u0269\u026a\3\2\2\2\u026ao\3\2\2\2\u026b\u026c\5F$\2\u026c\u026d"+
		"\5j\66\2\u026d\u026e\5L\'\2\u026e\u026f\7\22\2\2\u026f\u0270\5l\67\2\u0270"+
		"\u0271\5`\61\2\u0271\u0272\5n8\2\u0272\u0278\3\2\2\2\u0273\u0274\5\u0096"+
		"L\2\u0274\u0275\7\n\2\2\u0275\u0276\5`\61\2\u0276\u0278\3\2\2\2\u0277"+
		"\u026b\3\2\2\2\u0277\u0273\3\2\2\2\u0278q\3\2\2\2\u0279\u027a\5\u0096"+
		"L\2\u027a\u027b\7\n\2\2\u027b\u027c\5`\61\2\u027cs\3\2\2\2\u027d\u027e"+
		"\t\b\2\2\u027eu\3\2\2\2\u027f\u0280\78\2\2\u0280w\3\2\2\2\u0281\u0282"+
		"\t\t\2\2\u0282y\3\2\2\2\u0283\u028d\7\'\2\2\u0284\u028d\7(\2\2\u0285\u028d"+
		"\7\67\2\2\u0286\u028d\5P)\2\u0287\u028d\7;\2\2\u0288\u028d\5v<\2\u0289"+
		"\u028d\7<\2\2\u028a\u028d\5x=\2\u028b\u028d\7\34\2\2\u028c\u0283\3\2\2"+
		"\2\u028c\u0284\3\2\2\2\u028c\u0285\3\2\2\2\u028c\u0286\3\2\2\2\u028c\u0287"+
		"\3\2\2\2\u028c\u0288\3\2\2\2\u028c\u0289\3\2\2\2\u028c\u028a\3\2\2\2\u028c"+
		"\u028b\3\2\2\2\u028d{\3\2\2\2\u028e\u028f\t\n\2\2\u028f}\3\2\2\2\u0290"+
		"\u0291\7\20\2\2\u0291\177\3\2\2\2\u0292\u0293\7$\2\2\u0293\u0081\3\2\2"+
		"\2\u0294\u0295\7\60\2\2\u0295\u0083\3\2\2\2\u0296\u02a7\7@\2\2\u0297\u02a7"+
		"\7A\2\2\u0298\u02a7\7B\2\2\u0299\u02a7\7C\2\2\u029a\u02a7\7D\2\2\u029b"+
		"\u02a7\7E\2\2\u029c\u02a7\7)\2\2\u029d\u02a7\7*\2\2\u029e\u02a7\7F\2\2"+
		"\u029f\u02a7\5\u0080A\2\u02a0\u02a7\5\u0082B\2\u02a1\u02a7\7G\2\2\u02a2"+
		"\u02a7\7H\2\2\u02a3\u02a7\7I\2\2\u02a4\u02a7\5~@\2\u02a5\u02a7\5|?\2\u02a6"+
		"\u0296\3\2\2\2\u02a6\u0297\3\2\2\2\u02a6\u0298\3\2\2\2\u02a6\u0299\3\2"+
		"\2\2\u02a6\u029a\3\2\2\2\u02a6\u029b\3\2\2\2\u02a6\u029c\3\2\2\2\u02a6"+
		"\u029d\3\2\2\2\u02a6\u029e\3\2\2\2\u02a6\u029f\3\2\2\2\u02a6\u02a0\3\2"+
		"\2\2\u02a6\u02a1\3\2\2\2\u02a6\u02a2\3\2\2\2\u02a6\u02a3\3\2\2\2\u02a6"+
		"\u02a4\3\2\2\2\u02a6\u02a5\3\2\2\2\u02a7\u0085\3\2\2\2\u02a8\u02ab\5P"+
		")\2\u02a9\u02ab\7;\2\2\u02aa\u02a8\3\2\2\2\u02aa\u02a9\3\2\2\2\u02aa\u02ab"+
		"\3\2\2\2\u02ab\u0087\3\2\2\2\u02ac\u02ad\5\u0086D\2\u02ad\u02ae\7J\2\2"+
		"\u02ae\u02af\5\u0086D\2\u02af\u0089\3\2\2\2\u02b0\u02b1\t\13\2\2\u02b1"+
		"\u008b\3\2\2\2\u02b2\u02b5\5\u008aF\2\u02b3\u02b5\7&\2\2\u02b4\u02b2\3"+
		"\2\2\2\u02b4\u02b3\3\2\2\2\u02b5\u008d\3\2\2\2\u02b6\u02ba\7\13\2\2\u02b7"+
		"\u02b9\5`\61\2\u02b8\u02b7\3\2\2\2\u02b9\u02bc\3\2\2\2\u02ba\u02b8\3\2"+
		"\2\2\u02ba\u02bb\3\2\2\2\u02bb\u02bd\3\2\2\2\u02bc\u02ba\3\2\2\2\u02bd"+
		"\u02be\7\f\2\2\u02be\u008f\3\2\2\2\u02bf\u02c1\5\u008eH\2\u02c0\u02bf"+
		"\3\2\2\2\u02c0\u02c1\3\2\2\2\u02c1\u0091\3\2\2\2\u02c2\u02c7\5\u008eH"+
		"\2\u02c3\u02c4\5\u0094K\2\u02c4\u02c5\5`\61\2\u02c5\u02c7\3\2\2\2\u02c6"+
		"\u02c2\3\2\2\2\u02c6\u02c3\3\2\2\2\u02c7\u0093\3\2\2\2\u02c8\u02c9\7P"+
		"\2\2\u02c9\u0095\3\2\2\2\u02ca\u02cc\7Q\2\2\u02cb\u02ca\3\2\2\2\u02cb"+
		"\u02cc\3\2\2\2\u02cc\u02d1\3\2\2\2\u02cd\u02ce\7T\2\2\u02ce\u02d0\7R\2"+
		"\2\u02cf\u02cd\3\2\2\2\u02d0\u02d3\3\2\2\2\u02d1\u02cf\3\2\2\2\u02d1\u02d2"+
		"\3\2\2\2\u02d2\u02d4\3\2\2\2\u02d3\u02d1\3\2\2\2\u02d4\u02d5\7T\2\2\u02d5"+
		"\u0097\3\2\2\2\u02d6\u02d8\7S\2\2\u02d7\u02d6\3\2\2\2\u02d8\u02d9\3\2"+
		"\2\2\u02d9\u02d7\3\2\2\2\u02d9\u02da\3\2\2\2\u02da\u0099\3\2\2\2S\u009b"+
		"\u00a0\u00a4\u00a9\u00ae\u00b2\u00b7\u00bc\u00c1\u00c8\u00cc\u00d2\u00db"+
		"\u00e1\u00e5\u00eb\u00ed\u00f5\u0101\u0105\u010a\u0115\u0129\u012e\u0130"+
		"\u0135\u0138\u0147\u014a\u0153\u0156\u0159\u015c\u015f\u016b\u0170\u0174"+
		"\u0181\u0184\u018b\u018e\u0195\u0198\u019b\u019e\u01a1\u01a9\u01ba\u01ca"+
		"\u01cf\u01d5\u01d8\u01df\u01e2\u01e5\u01ec\u01f6\u0204\u0208\u0222\u022a"+
		"\u0244\u0246\u024a\u0252\u025b\u025e\u0263\u0266\u0269\u0277\u028c\u02a6"+
		"\u02aa\u02b4\u02ba\u02c0\u02c6\u02cb\u02d1\u02d9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}