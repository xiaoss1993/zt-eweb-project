
package com.zt.eweb.framework.mybatis.core.binding.parser;

import com.zt.eweb.framework.mybatis.core.util.S;
import com.zt.eweb.framework.mybatis.core.util.V;
import java.util.ArrayList;
import java.util.List;
import net.sf.jsqlparser.expression.AllValue;
import net.sf.jsqlparser.expression.AnalyticExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.ArrayConstructor;
import net.sf.jsqlparser.expression.ArrayExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.CastExpression;
import net.sf.jsqlparser.expression.CollateExpression;
import net.sf.jsqlparser.expression.ConnectByRootOperator;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.ExtractExpression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.HexValue;
import net.sf.jsqlparser.expression.IntervalExpression;
import net.sf.jsqlparser.expression.JdbcNamedParameter;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.JsonAggregateFunction;
import net.sf.jsqlparser.expression.JsonExpression;
import net.sf.jsqlparser.expression.JsonFunction;
import net.sf.jsqlparser.expression.KeepExpression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.MySQLGroupConcat;
import net.sf.jsqlparser.expression.NextValExpression;
import net.sf.jsqlparser.expression.NotExpression;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.NumericBind;
import net.sf.jsqlparser.expression.OracleHierarchicalExpression;
import net.sf.jsqlparser.expression.OracleHint;
import net.sf.jsqlparser.expression.OracleNamedFunctionParameter;
import net.sf.jsqlparser.expression.OverlapsCondition;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.RangeExpression;
import net.sf.jsqlparser.expression.RowConstructor;
import net.sf.jsqlparser.expression.RowGetExpression;
import net.sf.jsqlparser.expression.SignedExpression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeKeyExpression;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.TimezoneExpression;
import net.sf.jsqlparser.expression.TranscodingFunction;
import net.sf.jsqlparser.expression.TrimFunction;
import net.sf.jsqlparser.expression.UserVariable;
import net.sf.jsqlparser.expression.VariableAssignment;
import net.sf.jsqlparser.expression.WhenClause;
import net.sf.jsqlparser.expression.XMLSerializeExpr;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseLeftShift;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseRightShift;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.IntegerDivision;
import net.sf.jsqlparser.expression.operators.arithmetic.Modulo;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.conditional.XorExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.ContainedBy;
import net.sf.jsqlparser.expression.operators.relational.Contains;
import net.sf.jsqlparser.expression.operators.relational.DoubleAnd;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.FullTextSearch;
import net.sf.jsqlparser.expression.operators.relational.GeometryDistance;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsBooleanExpression;
import net.sf.jsqlparser.expression.operators.relational.IsDistinctExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.JsonOperator;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MemberOfExpression;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.expression.operators.relational.RegExpMatchOperator;
import net.sf.jsqlparser.expression.operators.relational.SimilarToExpression;
import net.sf.jsqlparser.expression.operators.relational.TSQLLeftJoin;
import net.sf.jsqlparser.expression.operators.relational.TSQLRightJoin;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.ParenthesedSelect;
import net.sf.jsqlparser.statement.select.Select;

/**
 * 关联注解条件解析器
 *
 * @author
 * @version v2.0
 * @date 2019/3/30
 */
public class ConditionParser implements ExpressionVisitor {

  private List<String> errorMsgList = null;
  private List<Expression> expressList = new ArrayList<>(4);
  public ConditionParser() {
  }

  /**
   * 添加错误信息
   *
   * @param errorMsg
   */
  private void addError(String errorMsg) {
    if (errorMsgList == null) {
      errorMsgList = new ArrayList<>();
    }
    if (!errorMsgList.contains(errorMsg)) {
      errorMsgList.add(errorMsg);
    }
  }

  /**
   * 获取解析后的结果
   *
   * @return
   * @throws Exception
   */
  public List<Expression> getExpressList() throws Exception {
    if (V.notEmpty(errorMsgList)) {
      throw new Exception(S.join(errorMsgList, "; "));
    }
    return expressList;
  }

  @Override
  public void visit(AndExpression andExpression) {
    andExpression.getLeftExpression().accept(this);
    andExpression.getRightExpression().accept(this);
  }

  // ----- 支持的条件
  @Override
  public void visit(EqualsTo equalsTo) {
    if (!(equalsTo.getLeftExpression() instanceof Column)) {
      addError("=条件左侧必须为字段/列名");
    }
    expressList.add(equalsTo);
  }

  @Override
  public void visit(NotEqualsTo notEqualsTo) {
    if (!(notEqualsTo.getLeftExpression() instanceof Column)) {
      addError("!=条件左侧必须为字段/列名");
    }
    expressList.add(notEqualsTo);
  }

  @Override
  public void visit(DoubleAnd doubleAnd) {

  }

  @Override
  public void visit(Contains contains) {

  }

  @Override
  public void visit(ContainedBy containedBy) {

  }

  @Override
  public void visit(ParenthesedSelect parenthesedSelect) {

  }

  @Override
  public void visit(GreaterThan greaterThan) {
    if (!(greaterThan.getLeftExpression() instanceof Column)) {
      addError(">条件左侧必须为字段/列名");
    }
    expressList.add(greaterThan);
  }

  @Override
  public void visit(GreaterThanEquals greaterThanEquals) {
    if (!(greaterThanEquals.getLeftExpression() instanceof Column)) {
      addError(">=条件左侧必须为字段/列名");
    }
    expressList.add(greaterThanEquals);
  }

  @Override
  public void visit(MinorThan minorThan) {
    if (!(minorThan.getLeftExpression() instanceof Column)) {
      addError("<条件左侧必须为字段/列名");
    }
    expressList.add(minorThan);
  }

  @Override
  public void visit(MinorThanEquals minorThanEquals) {
    if (!(minorThanEquals.getLeftExpression() instanceof Column)) {
      addError("<=条件左侧必须为字段/列名");
    }
    expressList.add(minorThanEquals);
  }

  @Override
  public void visit(IsNullExpression isNullExpression) {
    if (!(isNullExpression.getLeftExpression() instanceof Column)) {
      addError("IsNull条件左侧必须为字段/列名");
    }
    expressList.add(isNullExpression);
  }

  @Override
  public void visit(IsBooleanExpression isBooleanExpression) {

  }

  @Override
  public void visit(InExpression inExpression) {
    if (!(inExpression.getLeftExpression() instanceof Column)) {
      addError("IN条件左侧必须为字段/列名");
    }
    expressList.add(inExpression);
  }

  @Override
  public void visit(FullTextSearch fullTextSearch) {

  }

  @Override
  public void visit(Between between) {
    if (!(between.getLeftExpression() instanceof Column)) {
      addError("Between条件左侧必须为字段/列名");
    }
    expressList.add(between);
  }

  @Override
  public void visit(OverlapsCondition overlapsCondition) {

  }

  @Override
  public void visit(LikeExpression likeExpression) {
    if (!(likeExpression.getLeftExpression() instanceof Column)) {
      addError("Like条件左侧必须为字段/列名");
    }
    expressList.add(likeExpression);
  }

  //------- 暂不支持的条件
  @Override
  public void visit(OrExpression orExpression) {
    addError("暂不支持 OR 关联条件");
  }

  @Override
  public void visit(XorExpression xorExpression) {
    addError("暂不支持 XOR 关联条件");
  }

  // ------ 忽略的条件
  @Override
  public void visit(Column tableColumn) {
  }

  @Override
  public void visit(ExpressionList expressionList) {
  }


  @Override
  public void visit(CaseExpression caseExpression) {
  }

  @Override
  public void visit(WhenClause whenClause) {
  }

  @Override
  public void visit(ExistsExpression existsExpression) {
  }

  @Override
  public void visit(MemberOfExpression memberOfExpression) {

  }

  @Override
  public void visit(AnyComparisonExpression anyComparisonExpression) {
  }

  @Override
  public void visit(Concat concat) {
  }

  @Override
  public void visit(Matches matches) {
  }

  @Override
  public void visit(BitwiseAnd bitwiseAnd) {
  }

  @Override
  public void visit(BitwiseOr bitwiseOr) {
  }

  @Override
  public void visit(BitwiseXor bitwiseXor) {
  }

  @Override
  public void visit(CastExpression cast) {
  }

  @Override
  public void visit(Modulo modulo) {
  }

  @Override
  public void visit(AnalyticExpression aexpr) {
  }

  @Override
  public void visit(ExtractExpression eexpr) {
  }

  @Override
  public void visit(IntervalExpression iexpr) {
  }

  @Override
  public void visit(OracleHierarchicalExpression oexpr) {
  }

  @Override
  public void visit(RegExpMatchOperator rexpr) {
  }

  @Override
  public void visit(JsonExpression jsonExpr) {
  }

  @Override
  public void visit(JsonOperator jsonExpr) {
  }

  @Override
  public void visit(UserVariable var) {
  }

  @Override
  public void visit(NumericBind bind) {
  }

  @Override
  public void visit(KeepExpression aexpr) {
  }

  @Override
  public void visit(MySQLGroupConcat groupConcat) {
  }

  @Override
  public void visit(RowConstructor rowConstructor) {
  }

  @Override
  public void visit(RowGetExpression rowGetExpression) {

  }

  @Override
  public void visit(OracleHint hint) {
  }

  @Override
  public void visit(TimeKeyExpression timeKeyExpression) {
  }

  @Override
  public void visit(DateTimeLiteralExpression literal) {
  }

  @Override
  public void visit(NotExpression aThis) {
  }

  @Override
  public void visit(NextValExpression aThis) {
  }

  @Override
  public void visit(CollateExpression aThis) {
  }

  @Override
  public void visit(SimilarToExpression aThis) {
  }

  @Override
  public void visit(ArrayExpression arrayExpression) {
  }

  @Override
  public void visit(ArrayConstructor arrayConstructor) {

  }

  @Override
  public void visit(VariableAssignment variableAssignment) {
  }

  @Override
  public void visit(XMLSerializeExpr xmlSerializeExpr) {
  }

  @Override
  public void visit(TimezoneExpression timezoneExpression) {
  }

  @Override
  public void visit(JsonAggregateFunction jsonAggregateFunction) {
  }

  @Override
  public void visit(JsonFunction jsonFunction) {
  }

  @Override
  public void visit(ConnectByRootOperator connectByRootOperator) {
  }

  @Override
  public void visit(OracleNamedFunctionParameter oracleNamedFunctionParameter) {
  }

  @Override
  public void visit(AllColumns allColumns) {

  }

  @Override
  public void visit(AllTableColumns allTableColumns) {

  }

  @Override
  public void visit(AllValue allValue) {

  }

  @Override
  public void visit(IsDistinctExpression isDistinctExpression) {
  }

  @Override
  public void visit(GeometryDistance geometryDistance) {
  }

  @Override
  public void visit(Select select) {

  }

  @Override
  public void visit(TranscodingFunction transcodingFunction) {

  }

  @Override
  public void visit(TrimFunction trimFunction) {

  }

  @Override
  public void visit(RangeExpression rangeExpression) {

  }

  @Override
  public void visit(TSQLLeftJoin tsqlLeftJoin) {

  }

  @Override
  public void visit(TSQLRightJoin tsqlRightJoin) {

  }

  @Override
  public void visit(BitwiseRightShift aThis) {
  }

  @Override
  public void visit(BitwiseLeftShift aThis) {
  }

  @Override
  public void visit(NullValue nullValue) {
  }

  @Override
  public void visit(Function function) {
  }

  @Override
  public void visit(SignedExpression signedExpression) {
  }

  @Override
  public void visit(JdbcParameter jdbcParameter) {
  }

  @Override
  public void visit(JdbcNamedParameter jdbcNamedParameter) {
  }

  @Override
  public void visit(DoubleValue doubleValue) {
  }

  @Override
  public void visit(LongValue longValue) {
  }

  @Override
  public void visit(HexValue hexValue) {
  }

  @Override
  public void visit(DateValue dateValue) {
  }

  @Override
  public void visit(TimeValue timeValue) {
  }

  @Override
  public void visit(TimestampValue timestampValue) {
  }

  @Override
  public void visit(Parenthesis parenthesis) {
  }

  @Override
  public void visit(StringValue stringValue) {
  }

  @Override
  public void visit(Addition addition) {
  }

  @Override
  public void visit(Division division) {
  }

  @Override
  public void visit(IntegerDivision integerDivision) {
  }

  @Override
  public void visit(Multiplication multiplication) {
  }

  @Override
  public void visit(Subtraction subtraction) {
  }
}
