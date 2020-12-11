package Net.Customer;
/**
 * Created by Defias on 2020/08.
 * Description: 一个简单的投票协议 -- 完全自定义

 一个客户端向服务器发送了一个请求消息，消息中包含了一个候选人ID，范围是0~1000

 程序支持两种请求:
 一种是查询(inquiry)，即向服务器询问给定候选人当前获得的投票总数。服务器发回一个响应消息，包含了原来的候选人ID和该候选人当前
 (查询请求收到时)获得的选票总数
 另一种是投票(voting)请求，即向指定候选人投一票。服务器对这种请求也发回响应消息，包含了候选人ID和其获得的选票数

 VoteMsg.java类  消息
 VoteMsgCoder接口  对投票消息进行序列化和反序列化
 VoteMsgTextCoder类  基于文本的表示方法
 VoteMsgBinCoder类  二进制表示方法
 VoteServerTCP类 TCP投票服务器
 VoteClientTCP类  TCP投票客户端
 VoteServerUDP类  UDP投票服务器
 VoteClientUDP类 UDP投票客户端
 */

